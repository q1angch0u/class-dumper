## class dumper

dump classes from an encrypted jar which encrypted with JVMTI by using Java Instrumentation

usage: `java -Ddump_package=com.some.package -Ddump_out_folder=/tmp/decrypted -agentlib:JVMTI_MODULE -javaagent:class-dumper.jar -jar encrypted.jar`