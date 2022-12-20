# Model Card：facenet
## Model Introduction
FaceNet: A Unified Embedding for Face Recognition and Clustering
facenet first detects the key points of the face in the input image. After the face is detected, the key points of the face are aligned (transformed), and then the face is cropped out and input into the neural network. Finally, the embedded features of the face (hyperspherical Euclidean space) are output, which can be used for clustering, similarity detection and classification.

![image](https://user-images.githubusercontent.com/114071791/208710656-f567d955-4108-4c8e-8852-7d89a9028f4f.png)

## Model principle

All the facenet models are trained using a loss called triplet loss. This loss ensures that the model gives closer embeddings for same people and farther embeddings for different people.
The models are trained on a huge amount of images out of which triplets are generated.


The model directly learns a mapping from face images to a compact Euclidean space where distances directly correspond to a measure of face similarity. Facenet uses the deep convolutional network to convert the input face image into a vector, and then calculates the Euclidean distance between the two vectors with the vectors of each face in the data set. For the face image of the same person, the Euclidean distance between the corresponding two vectors should be relatively small, and vice versa.

### Facenet Network structure

NN1/NN2/NN3/NN4/NNS1/NNS2（Different convolutional neural networks）->L2 normalization->Embedding->The Triplet Loss and the gradient of each parameter are calculated, and the weights are updated


### Full name of each neural network and final accuracy on verification set when using this network as facenet's convolutional neural network:

NN1 (Zeiler&Fergus 220×220)     87.9% ± 1.9
NN2 (Inception 224×224)         89.4% ± 1.6
NN3 (Inception 160×160)         88.3% ± 1.7
NN4 (Inception 96×96)           82.0% ± 2.3
NNS1 (mini Inception 165×165)   82.4% ± 2.4
NNS2 (tiny Inception 140×116)   51.9% ± 2.9

### Embedding

The Embedding layer can be understood as a mapping relationship, that is, the feature is mapped from the original feature space to a new feature space, and the new feature can be called an embedding of the original feature.The main purpose of Embedding is to reduce the dimensionality of sparse features.

In the following example, the first matrix on the left is the one_hot coded 2x6 matrix, which is entered into the fully connected layer with three nodes to obtain the reduced dimensional output matrix on the right. The original 6-dimensional one_hot coding vector is reduced to 3-dimensional vector through the Embedding layer.

![image](https://user-images.githubusercontent.com/114071791/208705890-2330e68a-97d4-4d4c-975d-e1cc098a6476.png)

### Triplet Loss

In order to solve the defect of directly using Euclidean distance as loss function, facenet uses triplet loss function.

Three face images were extracted from the training data each time. The first image was labeled xai (fixed picture a), the second image was labeled xpi (positive sample picture b) and the third image was labeled xni (negative sample picture n). a and b correspond to an image of the same person, while n is an image of another person's face. Picture a and picture p are a positive sample pair, picture A and picture n are a negative sample pair.

We choose a sample that is least like the Positive sample from the positive sample (b), and a sample that is most like the positive sample from the Negative sample (n) (that is, samples with different faces that are closest to the fixed picture A are most likely to be confused), and then the calculated loss distance is the maximum distance. It is enough to optimize such loss function. When the values calculated in this way can meet the requirements, other samples can also meet the requirements.

![image](https://user-images.githubusercontent.com/114071791/208702632-b747c6c0-578a-4e42-92e5-72358ce83e6f.png)


## Model Usege

After calculating the embedding of the picture, we can conduct clustering, prediction and other operations. Try it out at the following link:
https://github.com/davidsandberg/facenet


## Some examples show:

We can verify facenet on the lfw dataset.

https://github.com/davidsandberg/facenet/wiki/Validate-on-lfw

The test is ran using validate_on_lfw:
python src/validate_on_lfw.py \
~/datasets/lfw/lfw_mtcnnpy_160 \
~/models/facenet/20180402-114759 \
--distance_metric 1 \
--use_flipped_images \
--subtract_mean \
--use_fixed_image_standardization

This will
a) load the model,
b) load and parse the text file with the image pairs,
c) calculate the embeddings for all the images (as well as their horizontally flipped versions) in the test set,
d) calculate the accuracy, validation rate (@FAR=-10e-3), the Area Under Curve (AUC) and the Equal Error Rate (EER) performance measures.

A typical output from the the test looks like this:

Model directory: /home/david/models/20180402-114759/
Metagraph file: model-20180402-114759.meta
Checkpoint file: model-20180402-114759.ckpt-275
Runnning forward pass on LFW images
........................
Accuracy: 0.99650+-0.00252
Validation rate: 0.98367+-0.00948 @ FAR=0.00100
Area Under Curve (AUC): 1.000
Equal Error Rate (EER): 0.004


## Limitations:

1.The triplet loss training face model usually requires a very large face data set to achieve good results. In addition, the convergence rate of the model is also slow.

2.facenet was developed earlier, so some libraries have stricter requirements (tensorflow=1.7.0, scipy=1.2.1). These libraries may not be compatible with advanced python environments, and it is important to note when creating environments.

## More information

https://github.com/davidsandberg/facenet
