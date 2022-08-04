#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_ineedpizzabeer_utils_Keys_apiKeyYelp(JNIEnv *env, jobject thiz) {
    std::string YelpApiKey = "nIz8iThEd_wNKHxlzOUz2hHsVE1AjOuUazDgWBxe0c-umjNp3iREHe9b5_oSo5UBuwwwABb_zrlMGq7HyY3CFo50kMo1do8okTwqVzct2rZaZHwZk4gOZZIbLACkYnYx";
    return env->NewStringUTF(YelpApiKey.c_str());
}