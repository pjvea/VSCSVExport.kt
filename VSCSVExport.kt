import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileWriter

/**
 * Created by PJ Vea on 10/14/17.
 * Copyright © 2017 Vea Software. All rights reserved.
 */

class VSCSVExport {
    fun exportCSV(fileName: String, columnTitles: Array<String>, dataByRow: Array<Array<String>>, activity: AppCompatActivity, intentTitle: String) {
        if (this.isPermissionGrantedForStorage(activity)) {
            val directory = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()
            val filePath = directory + File.separator + fileName
            val file = File(filePath)
            val writer: CSVWriter

            if (file.exists() && !file.isDirectory()) {
                val fileWriter = FileWriter(filePath, false)
                writer = CSVWriter(fileWriter)
            } else {
                writer = CSVWriter(FileWriter(filePath))
            }

            writer.writeNext(columnTitles)

            for (row in dataByRow) {
                writer.writeNext(row)
            }

            writer.close()

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/csv"
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
            activity.startActivity(Intent.createChooser(shareIntent, intentTitle))
        } else {
            print("Permission is required to create CSV file.")
        }
    }

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
}