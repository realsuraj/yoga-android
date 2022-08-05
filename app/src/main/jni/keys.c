#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_skroyal00000_dailyworkout_MainActivity_getApiHomeData(JNIEnv *env, jobject thiz) {
    return (*env)-> NewStringUTF(env, "https://dailyworkout.co.in/appApi/shopData.php");
}
JNIEXPORT jstring JNICALL
Java_com_skroyal00000_dailyworkout_MainActivity_getApiSignIn(JNIEnv *env, jobject thiz) {
return (*env)-> NewStringUTF(env, "https://dailyworkout.co.in/appApi/login.php");
}


JNIEXPORT jstring JNICALL
Java_com_skroyal00000_dailyworkout_MainActivity_getApiShowSingleData(JNIEnv *env, jobject thiz) {
return (*env)-> NewStringUTF(env, "https://dailyworkout.co.in/appApi/showDataParticular.php");
}

JNIEXPORT jstring JNICALL
Java_com_skroyal00000_dailyworkout_MainActivity_getApiUserShowExercise(JNIEnv *env, jobject thiz) {
return (*env)-> NewStringUTF(env, "https://dailyworkout.co.in/appApi/exercise.php");
}

JNIEXPORT jstring JNICALL
Java_com_skroyal00000_dailyworkout_MainActivity_getApiUserShowDiet(JNIEnv *env, jobject thiz) {
    return (*env)-> NewStringUTF(env, "https://dailyworkout.co.in/appApi/dietShow.php");
}

JNIEXPORT jstring JNICALL
Java_com_skroyal00000_dailyworkout_MainActivity_getApiUserUpdateData(JNIEnv *env, jobject thiz) {
    return (*env)-> NewStringUTF(env, "https://dailyworkout.co.in/appApi/updateUserData.php");
}