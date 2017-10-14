VSCSVExport.kt
============


VSCSVExport.kt is a simple Kotlin class that creates a CSV file.


Installation
============


Add `VSCSVExport.kt` to your project.


Setup
=====

Import your package at the top of the file.

```
package example.com.myapplication
```

AndroidManifest.xml
-------------------

Add `android:exported="true"` to `<application...>`.

Add `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />` after `</application>`.


opencsv
-------

Add `opencsv` to your `dependencies`.

```
compile 'com.opencsv:opencsv:3.8'
```

Usage
=====

Use the method `exportCSV` to generates a CSV file and open the share intent.

```
VSCSVExport().exportCSV("TestCSVFileName.csv", arrayOf("Title", "Other Title"), arrayOf(arrayOf("Apple", "iPhone X"), arrayOf("Google", "Pixel XL 2"))) {  uriFromFile, error ->
                if (error == VSCSVExportError.None) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/csv"
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uriFromFile)
                    this.startActivity(Intent.createChooser(shareIntent, "Share CSV"))
                } else {
                    print("Unspecified error when creating CSV file.")
                }
            }
```

Requesting Permissions
--- 

Add this method to your activity.

```
private fun isPermissionGrantedForStorage(activity: AppCompatActivity): Boolean {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true
            }
            activity.requestPermissions(arrayOf<String>(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            return false
        } else {
            return true
        }
    }
```

Use `isPermissionGrantedForStorage` to request permission if needed.

```
if (this.isPermissionGrantedForStorage(this)) {
    // permission granted
} else {
    // permission not granted
}
```