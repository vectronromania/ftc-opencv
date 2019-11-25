// C++ TeamCode glue file
// Functions here are supposed to adhere to JNI standards in order to be used
// from Java

#include <jni.h>

#include "teamcode.cpp"

#ifndef TEAMCODE_GLUE_CPP
#define TEAMCODE_GLUE_CPP

extern "C"
JNIEXPORT int JNICALL
Java_org_firstinspires_ftc_robotcontroller_custom_TeamCodeNativeGlue_testFunction(JNIEnv * env, jclass clazz) {
    return teamcode::test_function();
}

#endif // teamcode_glue
