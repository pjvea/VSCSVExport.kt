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
        val writer = if (file.exists() && !file.isDirectory()) {
            CSVWriter(FileWriter(file, false))
        } else {
            CSVWriter(FileWriter(file))
        }

        writer.writeNext(columnTitles)

        for (row in dataByRow) {
            writer.writeNext(row)
        }

        try {
            writer.flush()
            return VSCSVExportResult.Success(file)
        } catch (cause: Throwable) {
            return VSCSVExportResult.Error(Throwable("Write error."))
        } finally {
            writer.close()
        }
    }
}