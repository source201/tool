package tool;

/**
 * 这个类演示了一些关于xml的操作
 * 使用本类需要导入dom4j,下载官网：
 * @author 袁炜
 * @version 1.0
 */

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class xmlOp {

    /* 创建xml */




    /***
     * 创建Document 实例
     * @return 返回Document 实例
     */
    public static Document createDoc(){
        // 创建一个Document实例
        Document doc = DocumentHelper.createDocument();
        return doc;
    }



    /***
     * 创建指定名称的根节点
     * @param doc 要新增根节点的Document对象
     * @param elName 根节点名称
     * @return 根节点
     */
    public static Element createRootEl(Document doc, String elName){
        Element root = doc.addElement(elName);
        return root;
    }

    /***
     * 创建名称为“root"的根节点
     * @param doc 要新增根节点的Document对象
     * @return 根节点
     */
    public static Element createRootEl(Document doc){
        Element root = doc.addElement("root");
        return root;
    }

    /***
     * 创建元素
     * @param father 要新增元素的父元素
     * @param elName 新增元素的名称
     * @param attName 新增元素的Attribute名称
     * @param attValue 新增元素的Attribute值
     * @param textCon 新增元素的文本内容
     * @return 返回创建的元素
     */
    public static Element createEl(Element father, String elName, String attName, String attValue, String textCon){
        Element el=father.addElement(elName);
        el.addAttribute(attName,attValue);
        el.addText(textCon);
        return el;
    }
    /***
     * 创建元素
     * @param father 要新增元素的父元素
     * @param elName 新增元素的名称
     * @param attName 新增元素的Attribute名称
     * @param attValue 新增元素的Attribute值
     * @return 返回创建的元素
     */
    public static Element createEl(Element father, String elName, String attName, String attValue){
        Element el=father.addElement(elName);
        el.addAttribute(attName,attValue);
        return el;
    }
    /***
     * 创建元素
     * @param father 要新增元素的父元素
     * @param elName 新增元素的名称
     * @param textCon 新增元素的文本内容
     * @return 返回创建的元素
     */
    public static Element createEl(Element father, String elName, String textCon){
        Element el=father.addElement(elName);
        el.addText(textCon);
        return el;
    }

    /***
     * 创建元素
     * @param father 要新增元素的父元素
     * @param elName 新增元素的名称
     * @param att 新增元素的Attribute
     * @param textCon 新增元素的文本
     * @return 返回创建的元素
     */
    public static Element createEl(Element father, String elName, Attribute att, Text textCon){
        Element el=father.addElement(elName);
        el.add(att);
        el.add(textCon);
        return el;
    }

    /***
     * 创建元素
     * @param father 要新增元素的父元素
     * @param elName 新增元素的名称
     * @param att 新增元素的Attribute
     * @return 返回创建的元素
     */
    public static Element createEl(Element father, String elName, Attribute att){
        Element el=father.addElement(elName);
        el.add(att);
        return el;
    }
    public static Element createEl(Element father, String elName, ArrayList<Attribute> attList){
        Element el=father.addElement(elName);
        for(Attribute att: attList){
            el.add(att);
        }
        return el;
    }

    /***
     * 创建元素
     * @param father 要新增元素的父元素
     * @param elName 新增元素的名称
     * @param textCon 新增元素的文本
     * @return 返回创建的元素
     */
    public static Element createEl(Element father, String elName, Text textCon){
        Element el=father.addElement(elName);
        el.add(textCon);
        return el;
    }

    /***
     * 给元素添加子元素
     * @param father 父元素
     * @param child 子元素
     */
    public static void addEl(Element father, Element child){
        father.add(child);
    }

    /***
     * 将Document对象写入xml文件中
     * @param doc 要写的Document对象
     * @param xmlPath xml文件路径
     * @return 返回成功与否
     */
    public static boolean WriteXml(Document doc, String xmlPath){
        // 自定义xml样式
        OutputFormat format = new OutputFormat();
        format.setIndentSize(2);  // 行缩进
        format.setNewlines(true); // 一个结点为一行
        format.setTrimText(true); // 去重空格
        format.setPadText(true);
        format.setNewLineAfterDeclaration(false); // 放置xml文件中第二行为空白行

        // 输出xml文件
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream(new File(xmlPath)), format);
            writer.write(doc);
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {

        }
    }

    /***
     * 获取指定文件路径下的Document对象
     * @param xmlPath xml文件路径
     * @return 返回Document对象
     * @throws DocumentException
     */
    public static Document getDoc(String xmlPath) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlPath);
        return document;
    }

    /* 查找*/


    /***
     * 获取Root元素
     * @return 返回Root元素
     */
    public static Element getRootEl(Document doc){
        Element root = doc.getRootElement();
        return root;
    }



    /***
     * 遍历得到指定元素下的下一级子元素
     * @param father 指定的父元素
     * @return 返回遍历得到的子元素列表
     */
    public static ArrayList<Element> traverseEL(Element father){
        ArrayList<Element> elRList=new ArrayList<>();
        for (Iterator<Element> it = father.elementIterator(); it.hasNext();) {
            Element element = it.next();
            elRList.add(element);
        }
        return elRList;
    }

    /***
     * 遍历得到父元素father下所有名称为elName的元素
     * @param father 父元素
     * @param elName 查找的元素名称
     * @return 父元素father下所有名称为elName的元素列表
     */
    public static ArrayList<Element> traverseELByName(Element father, String elName){
        ArrayList<Element> elRList=new ArrayList<>();
        for (Iterator<Element> it = father.elementIterator(elName); it.hasNext();) {
            Element element = it.next();
            elRList.add(element);
        }
        return elRList;
    }
    /***
     * 遍历得到父元素列表fatherList中父元素下所有名称为elName的元素
     * @param fatherList 父元素列表
     * @param elName 查找的元素名称
     * @return 父元素列表fatherList下所有名称为elName的元素列表
     */
    public static ArrayList<Element> traverseElListByName(ArrayList<Element> fatherList, String elName){
        ArrayList<Element> elRList=new ArrayList<>();
        for(Element father:fatherList){
            for (Iterator<Element> it = father.elementIterator(elName); it.hasNext();) {
                Element element = it.next();
                elRList.add(element);
            }
        }
        return elRList;
    }

    /***
     * 提供父元素查找子元素，根据子元素name查找元素
     * @param father 父元素
     * @param ElName 子元素name
     * @return 找到返回指定子元素列表，未找到返回的列表长度为0
     */
    public static ArrayList<Element> findElByName(Element father, String ElName){
        ArrayList<Element> ElList=new ArrayList<>();
        for (Iterator<Element> it = father.elementIterator(); it.hasNext();) {
            Element element = it.next();
            String name=element.getName();
            if(name.equals(ElName)){
                ElList.add(element);
            }
        }
        return ElList;
    }

    /***
     * 提供父元素查找单个子元素，根据子元素的Attribute Name和Attribute value查找元素
     * @param father 父元素
     * @param attName 子元素的Attribute Name
     * @param attKeyValue 子元素的Attribute value
     * @return 找到返回指定子元素列表，未找到返回的列表长度为0
     */
    public static ArrayList<Element> findElByAtt(Element father, String attName, String attKeyValue){
        ArrayList<Element> ElList=new ArrayList<>();
        for (Iterator<Element> it = father.elementIterator(); it.hasNext();) {
            Element element = it.next();
            String vlaue=element.attributeValue(attName);
            if(vlaue.equals(attKeyValue)){
                ElList.add(element);
            }
        }
        return ElList;
    }


    /*删除*/

    /***
     * 删除父元素下的子元素
     * @param father 父元素
     * @param child 子元素
     * @return 成功返回true,失败返回false
     */
    public static boolean removeEl(Element father, Element child){
        try{
            father.remove(child);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /*修改*/

    /***
     * 给元素添加属性
     * @param el 元素
     * @param attName 属性名称
     * @param Value 属性值
     * @return 成功返回true,失败返回false
     */
    public static boolean addElAtt(Element el, String attName, String Value){
        try{
            el.addAttribute(attName,Value);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /***
     * 更改元素的属性值
     * @param el 元素
     * @param attName 元素属性名称
     * @param newValue 元素属性新的值
     * @return 成功返回true,失败返回false
     */
    public static boolean updateAtt(Element el, String attName, String newValue){

        try{
            el.setAttributeValue(attName,newValue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /***
     * 更改元素的text
     * @param el 元素
     * @param text 新的text值
     * @return 成功返回true,失败返回false
     */
    public static boolean updateText(Element el, String text){

        try{
            el.setText(text);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static void main(String[] args){

    }
}
