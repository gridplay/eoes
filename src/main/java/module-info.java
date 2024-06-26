module eoes {
    requires info.picocli;
    requires org.apache.logging.log4j;
    requires io.netty.all;
	requires com.fasterxml.jackson.databind;
	requires org.apache.commons.configuration2;
	requires java.sql;
	requires javax.persistence;

    opens eoes to info.picocli;
    
    exports eoes;
    exports eoes.Server;
    exports eoes.DB;
}
