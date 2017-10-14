VSCSVExport.kt
============


VSCSVExport.kt is a simple Kotlin class that generates a CSV file and allows for sharing of the file.


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

Download opencsv from [sourceforge](https://sourceforge.net/projects/opencsv/?source=typ_redirect).

1. Copy the `jar` file to your `libs` directory.
2. Change your folder structure in Android Studio from `Android` to `Project`.
3. Right clock on the `jar` file and choose `Add as library`.

Usage
=====

Use the method `exportCSV` to generates a CSV file and open the share intent.

```
VSCSVExport().exportCSV("TestCSVFileName.csv", arrayOf("Title", "Other Title"), arrayOf(arrayOf("Apple", "iPhone X"), arrayOf("Google", "Pixel XL 2")), this, "Share CSV")
```