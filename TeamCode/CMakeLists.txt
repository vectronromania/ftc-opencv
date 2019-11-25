cmake_minimum_required(VERSION 3.4.1)

set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

include_directories(${CMAKE_SOURCE_DIR}/src/main/jniIncludes)

add_library(libteamcode_native
            SHARED
            src/main/cpp/lib.cpp)

add_library(libopencv
            SHARED
            IMPORTED)

set_target_properties(libopencv
                      PROPERTIES
                      IMPORTED_LOCATION
                      ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI}/libopencv_java4.so)

find_library(liblog
             log)

target_link_libraries(libteamcode_native
                      ${liblog}
                      libopencv)
