package dinamica.util;

import java.io.File;

/*
 * http://my.oschina.net/webas/blog/108427
 */


public class MavenInstallJar {
	
    private String dir = "D:/bxdev/github/codegen4/lib";
    private String groupId = "com.aliyun";
    private String version = "1.0";
    
    private void outputInstallJarBatchFile() {
        File d = new File(dir);
        File[] fs = d.listFiles();
        for(File f : fs) {
            String name = f.getName().toLowerCase();
            String fileName = name;
            if(f.isFile() && name.endsWith(".jar")) {
                name = name.replaceAll(".jar", "");
                String[] ns = name.split("-");
                String artifactId = ns[0];
                StringBuffer sb = new StringBuffer();
                sb = new StringBuffer();
                sb.append("mvn install:install-file -DgroupId=").append(groupId);
                sb.append(" -DartifactId=").append(artifactId);
                sb.append(" -Dversion=").append(version);
                sb.append(" -Dfile=").append(fileName);
                sb.append(" -Dpackaging=jar -DgeneratePom=true");
                System.out.println(sb.toString());
            }
        }
    }
    
    private void printDependency() {
        File d = new File(dir);
        File[] fs = d.listFiles();
        for(File f : fs) {
            String name = f.getName().toLowerCase();
            if(f.isFile() && name.endsWith(".jar")) {
                name = name.replaceAll(".jar", "");
                String[] ns = name.split("-");
                String artifactId = ns[0];
                StringBuffer sb = new StringBuffer();
                sb.append("<dependency>\n");
                sb.append("  <groupId>").append(groupId).append("</groupId>\n");
                sb.append("  <artifactId>").append(artifactId).append("</artifactId>\n");
                sb.append("  <version>").append(version).append("</version>\n");
                sb.append("</dependency>");
                System.out.println(sb.toString());
            }
        }
    }
    public static void main(String[] args) {
        MavenInstallJar inst = new MavenInstallJar();
        inst.outputInstallJarBatchFile();
        inst.printDependency();
    }
}
