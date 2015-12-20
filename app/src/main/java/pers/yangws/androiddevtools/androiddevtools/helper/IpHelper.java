/**
 * 
 */
package pers.yangws.androiddevtools.androiddevtools.helper;

/**
 * @作者: 杨文松
 * @时间: 2015年12月11日
 * @描述: 
 * 
 */
public class IpHelper {
	
	/**
	 * 是否为ip地址
	 * @param value
	 * */
	 public static boolean isIpAddress(String value) {
	        int start = 0;
	        int end = value.indexOf('.');
	        int numBlocks = 0;
	        
	        while (start < value.length()) {
	            
	            if (end == -1) {
	                end = value.length();
	            }

	            try {
	                int block = Integer.parseInt(value.substring(start, end));
	                if ((block > 255) || (block < 0)) {
	                    return false;
	                }
	            } catch (NumberFormatException e) {
	                return false;
	            }
	            
	            numBlocks++;
	            
	            start = end + 1;
	            end = value.indexOf('.', start);
	        }
	        
	        return numBlocks == 4;
	}
	
}
