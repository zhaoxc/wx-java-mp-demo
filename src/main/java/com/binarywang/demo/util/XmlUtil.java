package com.binarywang.demo.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import java.io.InputStream;

/**
 * @Description: java类作用描述
 * @Author: zhaoxc
 * @CreateDate: 2021/10/9$ 13:34$
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class XmlUtil {
    /**
     19      *
     20      * @param inputXml
     21      * @param type
     22      * @return
     23      * @throws Exception
     24      */
     public static Object xml2Object(String inputXml, Class<?> type) throws Exception {
                if (null == inputXml || "".equals(inputXml)) {
                        return null;
                    }
                XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
                xstream.alias("xml", type);
         //xstream使用注解转换
         xstream.processAnnotations(type);
                return xstream.fromXML(inputXml);
            }

            /**
      *
      * @param inputStream
      * @param type
      * @return
      * @throws Exception
      */
             public static Object xml2Object(InputStream inputStream, Class<?> type) throws Exception {
                 if (null == inputStream) {
                         return null;
                     }
                XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
                xstream.alias("xml", type);
                 //xstream使用注解转换
                 xstream.processAnnotations(type);
                return xstream.fromXML(inputStream, type);
            }

             /**
      *
      * @param ro
      * @param types
      * @return
      * @throws Exception
      */
             public static String object2Xml(Object ro, Class<?> types) throws Exception {
                 if (null == ro) {
                         return null;
                     }
                XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
                xstream.alias("xml", types);
                 //xstream使用注解转换
                 xstream.processAnnotations(types);
                return xstream.toXML(ro);
            }

    public static void main(String[] args) {
                 TextMessage textMessage = new TextMessage();
                 textMessage.setFromUser("aa");
                 textMessage.setToUser("bb");
                 textMessage.setCreateTime(String.valueOf(System.currentTimeMillis()/1000));
                 textMessage.setContent("good luck");
        String xml = null;
        try {
            xml = XmlUtil.object2Xml(textMessage, TextMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(xml);
        try {
            TextMessage textMessage1 =   (TextMessage)XmlUtil.xml2Object(xml,TextMessage.class);
            System.out.println(textMessage1.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
