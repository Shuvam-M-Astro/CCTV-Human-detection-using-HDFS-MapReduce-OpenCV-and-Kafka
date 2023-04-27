import cv2

# Load the pre-trained human detection classifier
classifier = cv2.CascadeClassifier('haarcascade_fullbody.xml')

# Load the image
image = cv2.imread('image.jpg')

# Convert the image to grayscale
gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

# Detect human bodies in the image
bodies = classifier.detectMultiScale(gray_image)

# Draw rectangles around the detected bodies
for (x, y, w, h) in bodies:
    cv2.rectangle(image, (x, y), (x + w, y + h), (0, 0, 255), 2)

# Show the image with the detected bodies
cv2.imshow('Human detection', image)
cv2.waitKey(0)
cv2.destroyAllWindows()

