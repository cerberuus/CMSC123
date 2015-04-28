import java.io.UnsupportedEncodingException;

public class CharacterSetTest {
public static void main(String[] args) throws UnsupportedEncodingException {
String str="中";
int codepoint=str.codePointAt(0);

System.out.println("'"+str+"'的UNICODE编号(编码点,Unicode code point)="+codepoint+"[0x"+Integer.toHexString(codepoint)+"]");	
System.out.println();

byte[] bytes=str.getBytes("utf32");
System.out.println("'中'的UTF-32编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("utf16");
System.out.println("'中'的UTF-16编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("UTF-16BE");
System.out.println("'中'的UTF-16BE编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("UTF-16LE");
System.out.println("'中'的UTF-16LE编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("utf8");
System.out.println("'中'的UTF-8编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
byte b=bytes[0];
System.out.println("3字节UTF8的第1个字节:"+toBinaryString(b)+"[1110xxxx]");
b=bytes[1];
System.out.println("3字节UTF8的第2个字节:"+toBinaryString(b)+"[10xxxxxx]");
b=bytes[2];
System.out.println("3字节UTF8的第3个字节:"+toBinaryString(b)+"[10xxxxxx]");
bytes=str.getBytes("GBK");
System.out.println("'中'的gbk编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("GB2312");
System.out.println("'中'的GB2312编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("US-ASCII");
System.out.println("'中'的US-ASCII编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("ISO-8859-1");
System.out.println("'中'的ISO-8859-1编码单元长度为："+bytes.length+",内容为："+toHexString(bytes)+",可以看出被丢了一半");
System.out.println();

str="A";
bytes=str.getBytes("utf32");
System.out.println("'A'的UTF-32编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("utf16");
System.out.println("'A'的UTF-16编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("UTF-16BE");
System.out.println("'A'的UTF-16BE编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("UTF-16LE");
System.out.println("'A'的UTF-16LE编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("utf8");
System.out.println("'A'的UTF-8编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("GBK");
System.out.println("'A'的gbk编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));
bytes=str.getBytes("GB2312");
System.out.println("'A'的GB2312编码单元长度为："+bytes.length+",内容为："+toHexString(bytes));

System.out.println();

byte[] bytes2={0x4E,0x2D};
String str2=new String(bytes2,"UTF-16");
System.out.println("从0x4E2D 按UTF-16 转出来的："+str2);

byte[] bytes4={(byte)0xfe,(byte)0xff,0x4E,0x2D};
String str4=new String(bytes4,"UTF-16");
System.out.println("从0xFEFF4E2D 按UTF-16 转出来的："+str4);

byte[] bytes3={(byte) 0xE4,(byte) 0xB8,(byte) 0xAD};
String str3=new String(bytes3,"UTF8");
System.out.println("从0xE4B8AD 按UTF-8 转出来的："+str3);

}

public static String toBinaryString(byte b){
StringBuilder sb=new StringBuilder("");
String temp=Integer.toBinaryString(b&0xff);
sb.append("00000000".substring(temp.length())).append(temp);
return sb.toString();
}

public static String toHexString(byte[] bytes){
StringBuilder sb=new StringBuilder("0x");
for (int i=0;i<bytes.length;i++){
String temp=Integer.toHexString(bytes[i]&0xff);
sb.append((temp.length()==1)?"0"+temp:temp);
}
return sb.toString();
}
}