import android.net.Uri
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileWriter

/**
 * Created by PJ Vea on 10/14/17.
 * Copyright © 2017 Vea Software. All rights reserved.
 */

enum class VSCSVExportError {
    WriteError, None
}

class VSCSVExport {
    fun exportCSV(fileName: String, columnTitles: Array<String>, dataByRow: Array<Array<String>>, completion: (uriFromFile: Uri?, error: VSCSVExportError) -> Unit) {
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

        if (writer.checkError()) {
            completion(null, VSCSVExportError.WriteError)
        }

        writer.close()

        completion(Uri.fromFile(file), VSCSVExportError.None)
    }
}