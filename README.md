# 
Multiple Image Picker Library for Android (Kotlin)
 
 <img src="https://raw.githubusercontent.com/yusufkhan08070817/MultipleImagePicker/master/Minimalist%20Black%20and%20White%20Blank%20Paper%20Document.png" width="400" height="600">

### This library enables developers to easily implement a feature for selecting multiple images for a gallery in Android applications. It simplifies the process of image selection and retrieval in Kotlin-based Android projects.

## Usage
### Follow these steps to integrate the Multiple Image Picker library into your Android Kotlin project:

## Step 1: Add the JitPack repository to your root build.gradle

Add the following code at the end of the repositories block in your root build.gradle file:
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
```
## Step 2: Add the dependency
### Add the following dependency to your app-level build.gradle file:

```
implementation ("com.github.yusufkhan08070817:MultipleImagePicker:Tag")
```
Tag * 1.0.1 *

## Step 3: Implement the Image Picker

In your Kotlin activity or fragment, initialize the MultipleImagePicker instance:
```
lateinit var imagePicker: MultipleImagePicker

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    // Initialize the image picker
    imagePicker = MultipleImagePicker(this)
    
    // Request read and write permissions
    imagePicker.ImageReadAndWritePermision()
    
    // Start image picking process
    imagePicker.imagepick()
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    
    // Handle result of image picking process
    imagePicker.result(requestCode, resultCode, data) // Returns ArrayList<String>
}

override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    
    // Handle permission results
    imagePicker.onpermisionresult(
        requestCode,
        permissions,
        grantResults,
        {
            // Permission granted
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
        },
        {
            // Permission denied
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    )
}
```
# Additional Notes

Ensure that you handle permission requests and results appropriately.
The result() method returns an ArrayList of strings containing the paths of selected images.
Customize the permission grant/deny messages as per your application's requirements.
That's it! You have successfully integrated the Multiple Image Picker library into your Android Kotlin project. Feel free to explore further customization options as needed. If you encounter any issues or have suggestions for improvement, please open an issue on the GitHub repository. Happy coding!
