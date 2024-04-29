package com.revature.airbnb.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.config.SessionRepositoryCustomizer;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableJdbcHttpSession
public class SessionConfig implements BeanClassLoaderAware {
    
    // This class is used to configure the session to be stored in the database
    // This is done by using the @EnableJdbcHttpSession annotation
    // This annotation will create a table in the database to store the session information
    // The session information will be stored in the database and can be accessed by multiple instances of the application
    // This is useful when the application is running on multiple instances and the session information needs to be shared between them
    // The session information will be stored in the database and can be accessed by multiple instances of the application

    private final static String CREATE_SESSION_ATTRIBUTE_QUERY = "CREATE TABLE SPRING_SESSION (\n" + //
                "\tPRIMARY_ID CHAR(36) NOT NULL,\n" + //
                "\tSESSION_ID CHAR(36) NOT NULL,\n" + //
                "\tCREATION_TIME BIGINT NOT NULL,\n" + //
                "\tLAST_ACCESS_TIME BIGINT NOT NULL,\n" + //
                "\tMAX_INACTIVE_INTERVAL INT NOT NULL,\n" + //
                "\tEXPIRY_TIME BIGINT NOT NULL,\n" + //
                "\tPRINCIPAL_NAME VARCHAR(100),\n" + //
                "\tCONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)\n" + //
                ");\n" + //
                "\n" + //
                "CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);\n" + //
                "CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);\n" + //
                "CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);\n" + //
                "\n" + //
                "CREATE TABLE SPRING_SESSION_ATTRIBUTES (\n" + //
                "\tSESSION_PRIMARY_ID CHAR(36) NOT NULL,\n" + //
                "\tATTRIBUTE_NAME VARCHAR(200) NOT NULL,\n" + //
                "\tATTRIBUTE_BYTES JSONB NOT NULL,\n" + //
                "\tCONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),\n" + //
                "\tCONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE\n" + //
                ");";

    private ClassLoader classLoader;

    @Bean
	SessionRepositoryCustomizer<JdbcIndexedSessionRepository> customizer() {
		return (sessionRepository) -> sessionRepository.setCreateSessionAttributeQuery(CREATE_SESSION_ATTRIBUTE_QUERY);
	}

    @Bean("springSessionConversionService")
    public GenericConversionService springSessionConversionService(ObjectMapper objectMapper) { 
        ObjectMapper copy = objectMapper.copy(); 
        copy.registerModules(SecurityJackson2Modules.getModules(this.classLoader)); 
        GenericConversionService converter = new GenericConversionService();
        converter.addConverter(Object.class, byte[].class, new SerializingConverter(new JsonSerializer(copy))); 
        converter.addConverter(byte[].class, Object.class, new DeserializingConverter(new JsonDeserializer(copy))); 
        return converter;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    static class JsonSerializer implements Serializer<Object> {

        private final ObjectMapper objectMapper;

        JsonSerializer(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public void serialize(Object object, OutputStream outputStream) throws IOException {
            this.objectMapper.writeValue(outputStream, object);
        }

    }

    static class JsonDeserializer implements Deserializer<Object> {

        private final ObjectMapper objectMapper;

        JsonDeserializer(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public Object deserialize(InputStream inputStream) throws IOException {
            return this.objectMapper.readValue(inputStream, Object.class);
        }

    }
}