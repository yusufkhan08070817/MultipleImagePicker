# Multiple Image Picker Library for Android (Kotlin)
 
# <img src="https://raw.githubusercontent.com/yusufkhan08070817/MultipleImagePicker/master/Minimalist%20Black%20and%20White%20Blank%20Paper%20Document.png" width="400" height="600">

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
Tag  `1.0.1 `

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

# Terms and Conditions for Free Access to the Library:

#### By subscribing to the `KnowledgeCrave` YouTube channel, you acknowledge and agree to the following terms and conditions regarding the free access to the library:

#### Eligibility: Free access to the library is exclusively available to individuals who have subscribed to the KnowledgeCrave YouTube channel.

#### `Subscription` Verification: Access to the library will be granted upon verification of your subscription to the KnowledgeCrave YouTube channel. Verification may take up to 24 hours.

#### Non-Transferable: The free access to the library is non-transferable and may only be used by the individual who has subscribed to the KnowledgeCrave YouTube channel.

#### Duration: The free access to the library is valid as long as the subscriber remains actively subscribed to the KnowledgeCrave YouTube channel.

#### Revocation of Access: KnowledgeCrave reserves the right to revoke access to the library at any time without prior notice if it is determined that the subscriber has unsubscribed from the KnowledgeCrave YouTube channel or violated any of the terms and conditions outlined herein.

#### Usage: `Subscribers` are permitted to use the library for personal and non-commercial purposes only. Any unauthorized distribution or commercial use of the library is strictly prohibited.

### Modifications: KnowledgeCrave reserves the right to modify or update these terms and conditions at any time without prior notice. It is the subscriber's responsibility to review and adhere to the most current version of the terms and conditions.

By subscribing to the KnowledgeCrave YouTube channel and accessing the library, you agree to comply with these terms and conditions. Failure to comply may result in the termination of your access to the library.
# Additional Notes

Ensure that you handle permission requests and results appropriately.
The result() method returns an ArrayList of strings containing the paths of selected images.
Customize the permission grant/deny messages as per your application's requirements.
That's it! You have successfully integrated the Multiple Image Picker library into your Android Kotlin project. Feel free to explore further customization options as needed. If you encounter any issues or have suggestions for improvement, please open an issue on the GitHub repository. Happy coding!
