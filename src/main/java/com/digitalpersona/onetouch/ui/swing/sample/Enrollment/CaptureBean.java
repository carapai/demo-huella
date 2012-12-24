/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalpersona.onetouch.ui.swing.sample.Enrollment;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.DPFPCapturePriority;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.capture.event.DPFPErrorListener;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityListener;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusListener;
import com.digitalpersona.onetouch.capture.event.DPFPSensorListener;
import java.util.EventListener;

/**
 *
 * @author cecheverria
 */
public class CaptureBean implements DPFPCapture {

    private DPFPCapture capture = DPFPGlobal.getCaptureFactory().createCapture();

    public DPFPCapturePriority getPriority() {
        return capture.getPriority();
    }

    public void setPriority(DPFPCapturePriority dpfpcp) {
        capture.setPriority(dpfpcp);
    }

    public String getReaderSerialNumber() {
        return capture.getReaderSerialNumber();
    }

    public void setReaderSerialNumber(String string) {
        capture.setReaderSerialNumber(string);
    }

    public void startCapture() {
        capture.startCapture();
    }

    public void stopCapture() {
        capture.stopCapture();
    }

    public boolean isStarted() {
        return capture.isStarted();
    }

    public void addDataListener(DPFPDataListener dl) {
        capture.addDataListener(dl);
    }

    public void removeDataListener(DPFPDataListener dl) {
        capture.removeDataListener(dl);
    }

    public void addImageQualityListener(DPFPImageQualityListener dl) {
        capture.addImageQualityListener(dl);
    }

    public void removeImageQualityListener(DPFPImageQualityListener dl) {
        capture.removeImageQualityListener(dl);
    }

    public void addReaderStatusListener(DPFPReaderStatusListener dl) {
        capture.addReaderStatusListener(dl);
    }

    public void removeReaderStatusListener(DPFPReaderStatusListener dl) {
        capture.removeReaderStatusListener(dl);
    }

    public void addSensorListener(DPFPSensorListener dl) {
        capture.addSensorListener(dl);
    }

    public void removeSensorListener(DPFPSensorListener dl) {
        capture.removeSensorListener(dl);
    }

    public void addErrorListener(DPFPErrorListener dl) {
        capture.addErrorListener(dl);
    }

    public void removeErrorListener(DPFPErrorListener dl) {
        capture.removeErrorListener(dl);
    }

    public <T extends EventListener> T[] getListeners(Class<T> type) {
        return capture.getListeners(type);
    }
}
