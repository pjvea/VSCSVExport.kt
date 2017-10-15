import android.net.Uri
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileWriter

/**
 * Created by PJ Vea on 10/14/17.
 * Copyright © 2017 Vea Software. All rights reserved.
 */

sealed class VSCSVExportResult {
    data class Success(val file: File): VSCSVExportResult()
    data class Error(val cause: Throwable): VSCSVExportResult()
}

class VSCSVExport {
    fun exportCSV(file: File, columnTitles: Array<String>, dataByRow: Array<Array<String>>): VSCSVExportResult {
        val writer: CSVWriter

        if (file.exists() && !file.isDirectory()) {
            val fileWriter = FileWriter(file, false)
            writer = CSVWriter(fileWriter)
        } else {
            writer = CSVWriter(FileWriter(file))
        }

        writer.writeNext(columnTitles)

        for (row in dataByRow) {
            writer.writeNext(row)
        }

        if (writer.checkError()) {
            return VSCSVExportResult.Error(Throwable("Write error."))
        }

        writer.close()

        return VSCSVExportResult.Success(file)
    }
}