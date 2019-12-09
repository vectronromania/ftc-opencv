package org.firstinspires.ftc.robotcontroller.custom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

/**
 * Replacement activity for the default FtcRobotControllerActivity
 *
 * To be used with a custom OpenCV CameraView.
 */
public class CustomFtcRcActivity
        extends FtcRobotControllerActivity
        implements CameraBridgeViewBase.CvCameraViewListener2 {

    /**
     * Tag for logging purposes
     */
    private static final String TAG = "CustomFtcRCActivity";

    /**
     * Custom OpenCV CameraView
     */
    private JavaCameraView mCameraView;


    private BaseLoaderCallback mBaseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            super.onManagerConnected(status);
            Log.d(TAG, "mBaseLoaderCallback.onManagerConnected() called with: status = [" + status + "]");

            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    Log.i(TAG, "onManagerConnected: loaded OpenCV");
                    System.loadLibrary("libteamcode_native");
                    Log.i(TAG, "onManagerConnected: loaded custom native teamcode library");

//                    if (TeamCodeNativeGlue.testFunction() != 4)
//                        throw new AssertionError("teamcode test function did not return 2 + 2 = 4");

                    mCameraView.enableView();
                    break;
                default:
                    Log.e(TAG, "onManagerConnected: could not load OpenCV");
                    break;
            }
        }
    };


    @Override
    public void onCameraViewStarted(int width, int height) {
        Log.d(TAG, "onCameraViewStarted() called with: width = [" + width + "], height = [" + height + "]");
    }

    @Override
    public void onCameraViewStopped() {
        Log.d(TAG, "onCameraViewStopped() called");

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Log.d(TAG, "onCameraFrame() called with: inputFrame = [" + inputFrame + "]");
        return inputFrame.rgba();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        mCameraView = findViewById(R.id.custom_ftc_rc_camera_view);
        mCameraView.setVisibility(View.VISIBLE);
        mCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");

        if (!OpenCVLoader.initDebug()) {
            if (OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION, this, mBaseLoaderCallback)) {
                Log.i(TAG, "onResume: async init success");
            } else {
                Log.w(TAG, "onResume: async init failed");
            }
        } else {
            Log.i(TAG, "onResume: OpenCv library found inside the package, using it");
        }
    }
}
