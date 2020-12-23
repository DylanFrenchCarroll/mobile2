

import java.io.*


class JsonHelper{
    fun write( fileName: String, data: String) {

        var file = File(fileName)
        try {
            var outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: Exception) {
//            logger.error { "Cannot read file: " + e.toString() }
        }
    }

    fun read(fileName: String): String {
        var file = File(fileName)
        var str = ""
        try {
            var inputStreamReader = InputStreamReader(FileInputStream(file))
            if (inputStreamReader != null) {
                var bufferedReader = BufferedReader(inputStreamReader)
                var partialStr = StringBuilder()
                var done = false
                while (!done) {
                    var line = bufferedReader.readLine()
                    done = (line == null);
                    if (line != null) partialStr.append(line);
                }
                inputStreamReader.close()
                str = partialStr.toString()
            }
        } catch (e: FileNotFoundException) {
//            info { "Cannot Find file: " + e.toString() }
        } catch (e: IOException) {
//            info{ "Cannot Read file: " + e.toString() }
        }
        return str
    }

    fun exists(fileName: String): Boolean {
        var file = File(fileName)

        return file.exists()
    }
}