package com.example.android.ibot;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.hardware.camera2.CameraManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import java.io.IOException;
import java.security.Policy;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    android.hardware.Camera mcamera;
    SurfaceHolder holder;

    public CameraPreview(Context context, android.hardware.Camera camera) {
        super(context);
        mcamera = camera;
        holder = getHolder();
        holder.addCallback(this);
        //holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
           mcamera.stopPreview();
            mcamera.setDisplayOrientation(0);
            mcamera.setPreviewDisplay(holder);
            mcamera.startPreview();

        } catch (IOException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (holder.getSurface() == null) {
            // preview surface does not exist
            Toast.makeText(getContext(), "mybud", Toast.LENGTH_SHORT).show();
            return;
        }

        // stop preview before making changes
        try {
            mcamera.stopPreview();
        } catch (Exception e) {
            Toast.makeText(getContext(), "mybud", Toast.LENGTH_SHORT).show();

            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mcamera.setPreviewDisplay(holder);
            mcamera.setDisplayOrientation(90);
            mcamera.startPreview();


        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

}

