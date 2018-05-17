package XML;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Server.ClientServer;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.util.Base64;

public class XML_parser {
    public static boolean loginRequest(String username,String password){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element root = doc.createElement("Root");
            Attr atributo = doc.createAttribute("Operation");
            atributo.setValue("Log");
            root.setAttributeNode(atributo);
            Element user = doc.createElement("User");
            user.setAttribute("Name",username);;
            user.setAttribute("Password",password);
            root.appendChild(user);
            doc.appendChild(root);
            DOMSource domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            System.out.println(sw.toString());
            ClientServer.Server.send(sw.toString());
            String cosa = ClientServer.Server.receive();
            BufferedWriter out = new BufferedWriter(new FileWriter("tetst.txt"));
            out.write(cosa);
            out.close();
            InputSource source = new InputSource();
            source.setCharacterStream(new StringReader(cosa));
            doc = docBuilder.parse(source);
             domSource = new DOMSource(doc);
             transformer = TransformerFactory.newInstance().newTransformer();
             sw = new StringWriter();
             sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            System.out.println(sw.toString());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Root");
            System.out.print(nList.getLength());
            Element rootnode =(Element)nList.item(0);
            String result =rootnode.getAttribute("Result");
            System.out.print(result.contains("false"));
            return result.contains("true");
        }
        catch (Exception e){
return false;
        }
    }
    public static void get_songs(String method,String page){
        try{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element root = doc.createElement("Root");
        Attr atributo = doc.createAttribute("Operation");
        atributo.setValue("Songs");
        root.setAttributeNode(atributo);
        root.setAttribute("Method",method);
        root.setAttribute("Page",page);
        doc.appendChild(root);
        DOMSource domSource = new DOMSource(doc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);
        System.out.println(sw.toString());

        }catch (Exception e){

        }
    }
    public static boolean createAccount(String username, String id, String age, String password){
        try{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element root = doc.createElement("Root");
        Attr atributo = doc.createAttribute("Operation");
        atributo.setValue("Register");
        root.setAttributeNode(atributo);
        Element user = doc.createElement("User");
        user.setAttribute("Name",username);
        user.setAttribute("Password",password);
        user.setAttribute("Age",age);
        user.setAttribute("ID",id);
        root.appendChild(user);
        doc.appendChild(root);
        DOMSource domSource = new DOMSource(doc);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        transformer.transform(domSource, sr);
        System.out.println(sw.toString());
            ClientServer.Server.send(sw.toString());
        String recibido = ClientServer.Server.receive();
        System.out.print("LOL");
            InputSource source = new InputSource();
            source.setCharacterStream(new StringReader(recibido));
            doc = docBuilder.parse(source);
            domSource = new DOMSource(doc);
            transformer = TransformerFactory.newInstance().newTransformer();
            sw = new StringWriter();
            sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            System.out.println(sw.toString());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Root");
            System.out.print(nList.getLength());
            Element rootnode =(Element)nList.item(0);
            String result =rootnode.getAttribute("Result");
            System.out.print(result.contains("false"));
            return result.contains("true");
        }
        catch (Exception e){

        }
        return false;
    }
    public static void chunk(String filename,String chunk){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element root = doc.createElement("Root");
            Attr atributo = doc.createAttribute("Operation");
            atributo.setValue("Stream");
            root.setAttributeNode(atributo);
            root.setAttribute("Filename",filename);
            root.setAttribute("Chunk",chunk);
            doc.appendChild(root);
            DOMSource domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            System.out.println(sw.toString());
        }catch (Exception e){

        }
    }
public static void prueba(){
    try{
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    // root elements
    Document doc = docBuilder.newDocument();
    Element rootElement = doc.createElement("company");
    doc.appendChild(rootElement);

    // staff elements
    Element staff = doc.createElement("Staff");
    rootElement.appendChild(staff);

    // set attribute to staff element
    Attr attr = doc.createAttribute("id");
    attr.setValue("1");
    staff.setAttributeNode(attr);
//
    // shorten way
    // staff.setAttribute("id", "1");

    // firstname elements
    Element firstname = doc.createElement("firstname");
    firstname.appendChild(doc.createTextNode("yong"));
    staff.appendChild(firstname);

    // lastname elements
    Element lastname = doc.createElement("lastname");
    lastname.appendChild(doc.createTextNode("mook kim"));
    staff.appendChild(lastname);

    // nickname elements
    Element nickname = doc.createElement("nickname");
    nickname.appendChild(doc.createTextNode("mkyong"));
    staff.appendChild(nickname);

    // salary elements
    Element salary = doc.createElement("salary");
    salary.appendChild(doc.createTextNode("100000"));
    staff.appendChild(salary);

    // write the content into xml file
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(new File("C:\\file.xml"));

    // Output to console for testing
    // StreamResult result = new StreamResult(System.out);

    transformer.transform(source, result);

    System.out.println("File saved!");}
    catch (Exception e){
        return;
    }
}
public static void pruebamp3(){
    try{
        File fXmlFile = new File("/home/kenneth/CLionProjects/Progra.2.datos.c-/cmake-build-debug/LOLA.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("rootnode");
        Element archive = (Element)nList.item(0);
        String meme = archive.getAttribute("version");
        System.out.print(meme);
//        byte[] decodedBytes = Base64.getDecoder().decode(meme);
//        File fileToWriteTo = new File("prueba.mp3");

    //    Files.write(decodedBytes, fileToWriteTo);
    }catch (Exception e){

    }
}
public static String getFile(String path)throws IOException {

    File file = new File(path);
    byte[] bytes = loadFile(file);
    byte[] encoded = Base64.getEncoder().encode(bytes);
    String encodedString = new String(encoded);

    return encodedString;
}
    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }
    public static String getXML_Archive(String path){
        try {
            String file = getFile(path);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            final int mid = file.length() / 2; //get the middle of the String
            String[] parts = {file.substring(0, mid),file.substring(mid)};
            Element root = doc.createElement("Root");
            Attr atributo = doc.createAttribute("Operation");
            atributo.setValue("Upload");
            Element file_node = doc.createElement("Archive");
            file_node.setAttribute("File",file);
            root.appendChild(file_node);
            doc.appendChild(root);
            DOMSource domSource = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            System.out.println(sw.toString());
            return  sw.toString();
        }catch (Exception e){
return null;
        }
    }
}
