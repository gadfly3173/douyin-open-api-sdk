package vip.gadfly.tiktok.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtil {
    private static final int BUFFER = 1024;
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class
            .getName());

    /**
     * 收集文件或文件夹中的文件(包括子文件夹)到files中
     *
     * @param files     用于收集的List<File>
     * @param fileOrDir 指定一个文件名称或者文件夹
     */
    public static void getFiles(final List<File> files, File fileOrDir,
                                ServletContext sc) {
        getFiles(files, fileOrDir, sc, null);
    }

    /**
     * 收集文件或文件夹中的文件(包括子文件夹)到files中
     *
     * @param files     用于收集的List<File>
     * @param fileOrDir 指定一个文件名称或者文件夹
     */
    public static void getFiles(final List<File> files, File fileOrDir,
                                ServletContext sc, FilenameFilter filter) {
        if (fileOrDir == null) {
            return;
        }
        if (fileOrDir.isFile()) {
            if (filter == null || filter.accept(null, fileOrDir.getName())) {
                files.add(fileOrDir);
            }
        } else {
            File[] fs = fileOrDir.listFiles();
            for (File f : fs) {
                getFiles(files, f, sc, filter);
            }
        }
    }

    /**
     * 获取文件真实路径
     *
     * @param filename 文件名称（可以包含相对路径）
     * @param sc       如果值传null则返回相对于classpath目录的文件路径，有传sc则返回相对于sc上下文环境的文件路径
     * @return
     */
    public static String getFilePath(String filename, ServletContext sc) {

        if (sc == null) {
            try {
                return FileUtil.class.getResource(filename).getFile();
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        try {
            URL url = sc.getResource(filename);
            return url.getFile();
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件对象
     *
     * @param filename 文件名称（可以包含相对路径）
     * @param sc       如果值传null则从相对于classpath目录的路径去获取文件，有传sc则在相对于sc上下文环境的文件路径去获取文件
     * @return
     */

    public static File getFile(String filename, ServletContext sc) {
        String pathname = getFilePath(filename, sc);
        File f;
        if (pathname != null) {
            f = new File(pathname);
            if (f.exists())// 如果成功取得文件就返回
            {
                return f;
            }
        }
        // 如果以上方法取不到文件，则尝试另外的方法
        if (sc != null) {
            pathname = sc.getRealPath(filename);
            log.debug("文件路径2：" + pathname);
            f = new File(pathname);
            if (f.exists()) {
                return f;
            }
        }
        log.error("文件不存在：" + filename);
        return null;
    }

    /**
     * 删除文件
     *
     * @param shortpath 文件路径(绝对路径,去掉前面的全局配置路径)
     */
    public static void deleteFile(String path) {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        if (StringUtils.isEmpty(path)) {
            return;
        }
        String filepath = path;
        File file = new File(filepath);
        if (file != null && file.exists()) {
            file.delete();
        }

    }

    /**
     * 获取文件的文件名
     *
     * @param path 文件绝对路径
     * @return
     * @throws Exception
     */
    public static String getNameByPath(String path) throws Exception {
        if (path != null && path.indexOf(File.separator) >= 0) {
            String filename = path
                    .substring(path.lastIndexOf(File.separator) + 1);
            return filename;
        }
        return path;
    }

    /**
     * 读取文件并且返回包含文件内容的StringBuilder
     */
    public static StringBuilder readFileSB(String pathName) {
        return readFileSB(pathName, "utf-8");
    }

    /**
     * 读取文件并且返回包含文件内容的StringBuilder
     */
    public static StringBuilder readFileSB(String pathName, String charset) {
        File file = new File(pathName);
        InputStream fr = null;
        BufferedReader input = null;
        StringBuilder buffer = new StringBuilder();
        try {
            fr = new FileInputStream(file);
            input = new BufferedReader(new InputStreamReader(fr, charset));
            String text;
            while ((text = input.readLine()) != null) {
                if (buffer.length() > 0) {
                    buffer.append("\n");
                }
                buffer.append(text);
            }
        } catch (Exception e) {
        } finally {
            try {
                input.close();
                fr.close();
            } catch (Exception e) {
            }
        }
        return buffer;
    }

    public static String readFile(String pathName) {
        return readFile(pathName, "utf-8");
    }

    public static String readFile(InputStream in) {
        return readFile(in, "utf-8");
    }

    public static String readFile(InputStream in, String charset) {
        String content;
        InputStream fr = in;
        BufferedReader input = null;
        try {

            input = new BufferedReader(new InputStreamReader(fr, charset));
            StringBuffer buffer = new StringBuffer();
            String text;
            while ((text = input.readLine()) != null) {
                if (buffer.length() > 0) {
                    buffer.append("\n");
                }
                buffer.append(text);
            }
            content = buffer.toString();
        } catch (Exception e) {
            content = "";
        } finally {
            try {
                input.close();
                fr.close();
            } catch (Exception e) {
            }
        }
        return content;
    }

    /**
     * 读取指定文件的所有内容,以String返回
     *
     * @param pathname 要读取文件的全路径和全名称
     * @return 文件所有内内容
     */
    public static String readFile(String pathName, String charset) {
        String content;
        File file = new File(pathName);
        InputStream fr = null;
        BufferedReader input = null;
        try {
            fr = new FileInputStream(file);
            input = new BufferedReader(new InputStreamReader(fr, charset));
            StringBuffer buffer = new StringBuffer();
            String text;
            while ((text = input.readLine()) != null) {
                if (buffer.length() > 0) {
                    buffer.append("\n");
                }
                buffer.append(text);
            }
            content = buffer.toString();
        } catch (FileNotFoundException e) {
            log.error("找不到文件：" + pathName);
            e.printStackTrace();
            content = "";
        } catch (Exception e) {
            content = "";
        } finally {
            try {
                input.close();
                fr.close();
            } catch (Exception e) {
            }
        }
        return content;
    }

    /**
     * 将content写入指定文件里，如果文件不存在，则创建
     *
     * @param pathName 要写入文件的全路径和全名称
     * @param content  要写入的内容
     * @param append   是否将内容追加到文件末，否则复盖文件内容
     * @return 写入成功返回true
     */
    public static Boolean writeFile(String pathName, String content,
                                    Boolean append) {
        File file = null;
        FileWriter fw = null;
        BufferedWriter output = null;
        try {
            file = new File(pathName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file, append);
            output = new BufferedWriter(fw);
            output.write(content);

            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                output.close();
                fw.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 取得指定目录下的所有文件列表，包括子目录.
     *
     * @param baseDir File 指定的目录
     * @return 包含java.io.File的List
     */
    private static List<File> getSubFiles(File baseDir) {
        List<File> ret = new ArrayList<File>();
        File[] tmp = baseDir.listFiles();
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].isFile()) {
                ret.add(tmp[i]);
            }
            if (tmp[i].isDirectory()) {
                ret.addAll(getSubFiles(tmp[i]));
            }
        }
        return ret;
    }

    /**
     * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
     *
     * @param baseDir      java.lang.String 根目录
     * @param realFileName java.io.File 实际的文件名
     * @return 相对文件名
     */
    private static String getAbsFileName(String baseDir, File realFileName) {
        File real = realFileName;
        File base = new File(baseDir);
        String ret = real.getName();
        while (true) {
            real = real.getParentFile();
            if (real == null) {
                break;
            }
            if (real.equals(base)) {
                break;
            } else {
                ret = real.getName() + "/" + ret;
            }
        }
        return ret;
    }

    /**
     * zip压缩一个文件
     *
     * @param sourceFile 要压缩的文件名称(包括完整的路径)
     * @param targetFile 生成的压缩(zip)文件名称(包括完整的路径)
     * @throws Exception
     */
    public static void zipFile(String sourceFile, String targetFile)
            throws Exception {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
                targetFile));
        ZipEntry ze = null;
        byte[] buf = new byte[BUFFER];
        int readLen = 0;
        File file = new File(sourceFile);
        ze = new ZipEntry(file.getName());
        ze.setSize(file.length());
        ze.setTime(file.lastModified());
        zos.putNextEntry(ze);
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
            zos.write(buf, 0, readLen);
        }
        is.close();
        zos.close();
    }

    /**
     * zip压缩一个文件夹
     *
     * @param baseDir    要压缩的文件夹名称(包括全路径)
     * @param targetFile 生成的压缩(zip)文件名称(包括完整的路径)
     * @throws Exception
     */
    public static void zipDir(String baseDir, String targetFile)
            throws Exception {
        List<File> fileList = getSubFiles(new File(baseDir));
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
                targetFile));
        ZipEntry ze = null;
        byte[] buf = new byte[BUFFER];
        int readLen = 0;
        for (int i = 0; i < fileList.size(); i++) {
            File f = fileList.get(i);
            ze = new ZipEntry(getAbsFileName(baseDir, f));
            ze.setSize(f.length());
            ze.setTime(f.lastModified());
            zos.putNextEntry(ze);
            InputStream is = new BufferedInputStream(new FileInputStream(f));
            while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
                zos.write(buf, 0, readLen);
            }
            is.close();
        }
        zos.close();
    }

    /**
     * 解压缩zip文件到指定目录下
     *
     * @param zipFile
     * @param targetDir
     * @throws Exception
     */
    public static void unZipFile(String zipFile, String targetDir)
            throws Exception {
        String separator = System.getProperty("file.separator");
        if (!separator.equals(targetDir.substring(targetDir.length() - 1))) {
            targetDir += separator;
        }
        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        ZipFile zfile = new ZipFile(zipFile);
        @SuppressWarnings("rawtypes")
        Enumeration zList = zfile.entries();
        ZipEntry ze = null;
        byte[] buf = new byte[BUFFER];
        while (zList.hasMoreElements()) {
            ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                File f = new File(targetDir + ze.getName());
                f.mkdir();
                continue;
            }
            OutputStream os = new BufferedOutputStream(new FileOutputStream(
                    targetDir + ze.getName()));
            InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
            int readLen = 0;
            while ((readLen = is.read(buf, 0, BUFFER)) != -1) {
                os.write(buf, 0, readLen);
            }
            is.close();
            os.close();
        }
        zfile.close();
    }

    /**
     * 复制文件
     *
     * @param fileFrom 源文件
     * @param fileTo   目标文件
     * @return
     */
    public static boolean copy(String fileFrom, String fileTo) {
        try {
            FileInputStream in = new FileInputStream(fileFrom);
            FileOutputStream out = new FileOutputStream(fileTo);
            byte[] bt = new byte[1024];
            int count;
            while ((count = in.read(bt)) > 0) {
                out.write(bt, 0, count);
            }
            in.close();
            out.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static String getFilenameExtension(String filename) {
        int start = filename.lastIndexOf(".");
        return filename.substring(start == -1 ? 1 : start + 1
        );

    }

    public static void main(String[] args) {
        String path = "/conf/spring/applicationContext.xml";
        System.out.println(FileUtil.getFile(path, null));
        System.out.println(getFilenameExtension("sdfs.123.ss"));
    }
}
