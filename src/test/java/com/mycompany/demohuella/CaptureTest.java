/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demohuella;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.annotations.Test;

/**
 *
 * @author cecheverria
 */
public class CaptureTest {

    @Test
    public void capture() {
        final Executor executor = Executors.newCachedThreadPool();

        DPFPEnrollment enrollment = DPFPGlobal.getEnrollmentFactory().createEnrollment();
        DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();

        capturer.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {

                executor.execute(new Runnable() {
                    public void run() {
                        makeReport("The fingerprint sample was captured.");
                        setPrompt("Scan the same fingerprint again.");
                        process(e.getSample());
                    }
                });
            }
        });
        capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                executor.execute(new Runnable() {
                    public void run() {
                        makeReport("The fingerprint reader was connected.");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                executor.execute(new Runnable() {
                    public void run() {
                        makeReport("The fingerprint reader was disconnected.");
                    }
                });
            }
        });
        capturer.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                executor.execute(new Runnable() {
                    public void run() {
                        makeReport("The fingerprint reader was touched.");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                executor.execute(new Runnable() {
                    public void run() {
                        makeReport("The finger was removed from the fingerprint reader.");
                    }
                });
            }
        });
        capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
            @Override
            public void onImageQuality(final DPFPImageQualityEvent e) {
                executor.execute(new Runnable() {
                    public void run() {
                        if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD)) {
                            makeReport("The quality of the fingerprint sample is good.");
                        } else {
                            makeReport("The quality of the fingerprint sample is poor.");
                        }
                    }
                });
            }
        });

        capturer.startCapture();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CaptureTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void makeReport(String s) {
        System.out.println(s);
    }

    public void setPrompt(String s) {
        System.out.println(">>>" + s);
    }

    public void process(DPFPSample sample) {
        System.out.println(sample);
    }
}
