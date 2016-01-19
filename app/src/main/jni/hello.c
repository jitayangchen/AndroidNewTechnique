//
// Created by yangchen on 16-1-19.
//

#include <string.h>
#include <jni.h>

jstring JNICALL Java_com_pepoc_androidnewtechnique_jni_JniActivity_helloJni
        (JNIEnv *env, jobject thiz) {
        return (*env) -> NewStringUTF(env, "Hello Jni");
}

