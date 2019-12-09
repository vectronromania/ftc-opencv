// C++ TeamCode file
// For actual implementation only

#include "opencv2/core.hpp"
#include <opencv2/imgproc.hpp>

#ifndef TEAMCODE_CPP
#define TEAMCODE_CPP

namespace teamcode {

    int test_function() {
        return 2 + 2;
    }

    void grayscale_image(cv::Mat & image) {
        cv::cvtColor(image, image, cv::COLOR_RGB2GRAY);
    }

}

#endif // TEAMCODE_CPP
