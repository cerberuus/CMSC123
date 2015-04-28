//Discussion between the code point and code unit. So this is the summary of our review:
/* 
 * The String of Java is consisted of char sequences. The char is using the UTF-16 code unit to represent the unicode code point. Most unicode char can only use one code unit to represent. However, sometimes some unicode 
 * char will use two code unit to represent. This is an example. The str use the length() to find the length of the utf-16 code unit. However, the method codePointCount(startpoint, length of string) to count the length of
 * code point.
 * 
 * the function of codePointAt:
 * 	1. it will return the unicode integer value
 *  2. it will using the index of the length() range
 *  
 *  the function of offsetByCodePoints(0, i):
 *  1. public int offsetByCodePoints(int index, int codePointOffset), so the offset in the code point, produce the index in code unit.
 *  2. Then use the codePointAt(index) to get the right result, and if use the charAt will not give the right answer.
 *  3. the function codePointAt will have the ability to distinguish whether it is the supplemnetary code point's first part or second part and print out the right result.
 */
public class CharacterSetTest {
	public static void main(String[] args) {
		String str = "A" + "\uD835\uDD0A" + "B" + "C";
		int cpCount = str.codePointCount(0, str.length());
		System.out.println("The length of the code point:" + cpCount + ", by using the function of codePointCount.");
        System.out.println("The length of the code unit:" + str.length() + ", by using the function of length.");
		
        System.out.println("==== The code point display by using the codePointAt ====");
		for(int i = 0; i < cpCount; i++) {
        	int cp = str.codePointAt(i);
        	System.out.println((char)cp + "--" + i + "--" + cp);
        }
		System.out.println("==== The code point display by using the codePointAt in the right way ====");
        for(int i=0;;){
            if(i<str.length()){
                int cp = str.codePointAt(i);
                System.out.println((char)cp +"--"+ cp);
                if(Character.isSupplementaryCodePoint(cp)){ /// using this function to distinguish
                    i += 2;
                }else{
                    i += 1;
                }
            }else{
                break;
            }    
        }
        
        System.out.println("==== The code point display by using the charAt ====");
        for(int i = 0; i < str.length(); i++) {
        	char cha = str.charAt(i);
        	System.out.println(cha + "--" + i);
        }
        
        System.out.println("==== The code point display by using the offsetByCodePoints ====");
        for(int i = 0; i < cpCount; i++) {
        	int cp = str.offsetByCodePoints(0, i);
        	System.out.println((char)str.codePointAt(cp) + "--" + i + "--" + cp);
        }
         
    }
}