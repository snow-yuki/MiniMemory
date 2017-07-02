package com.marinesnow.minimemory;

import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/7/2.
 */
public class BuleString {

    /**
     * judge if the input string has star sign
     * if has return false else return true
     *
     * @param content
     * @return
     */
    private  static boolean notHasStar( String content ) {
        for( int i = 0; i < content.length(); i++ ) {
            char tmp = content.charAt( i );
            if( tmp == '*' ) {
                return false;
            }
        }
        return true;
    }

    /**
     * input the path of data file
     * return the list of BuleData
     * BuleData contains one string which stores the content and ont integer to record the score
     *
     * @param
     * @return
     */
    public static ArrayList<BuleData> generateList( String content ) {
        try {
            ArrayList<BuleData> list = new ArrayList<>();
            byte[] bytes = new byte[2*1024];
            StringBuilder sb = new StringBuilder();
            for( int i = 0; i < content.length(); i++ ) {
                char tmp1 = content.charAt( i );
                if( tmp1 == 10 || tmp1 == ' ' ) {
                    continue;
                }
                if( tmp1 >= '0' && tmp1 <= '9' ) {
                    i += 2;
                    list.add( new BuleData( sb.toString(), 0 ) );
                    sb = new StringBuilder();
                } else {
                    sb.append( tmp1 );
                }
            }
            for( int i = 0; i < list.size(); i++ ) {
                BuleData buleData = list.get( i );
                if( notHasStar( buleData.content ) ) {
                    list.remove( i );
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * input the string with answers
     * output the string without answers replaced by "_____"
     *
     * @param content
     * @return
     */
    public static String toTextView( String content ) {
        boolean flag = true;
        String replace = "_____";
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < content.length(); i++ ) {
            char c = content.charAt( i );
            if( c == '*' ) {
                i++;
                flag = !flag;
                sb.append( replace );
                continue;
            }
            if( flag ) {
                sb.append( c );
            }
        }
        return sb.toString();
    }

    /**
     * remove four star sign in the string
     * example:
     *      **德**是人才素质的灵魂。**智**是人才素质的基本内容。**体**是人才素质的基础。**美**是人才素质的综合体现。
     *      first time:
     *      德是人才素质的灵魂。**智**是人才素质的基本内容。**体**是人才素质的基础。**美**是人才素质的综合体现。
     *      second time:
     *      德是人才素质的灵魂。智是人才素质的基本内容。**体**是人才素质的基础。**美**是人才素质的综合体现。
     *
     * @param content
     * @return
     */
    public static String removeStar( String content ) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for( int i = 0; i < content.length(); i++ ) {
            char c = content.charAt( i );
            if( c == '*' && count < 4  ) {
                count++;
            } else {
                sb.append( c );
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String path = "e:/list.txt";
        String content = "**德**是人才素质的灵魂。**智**是人才素质的基本内容。**体**是人才素质的基础。**美**是人才素质的综合体现。";
        System.out.println(BuleString.removeStar(BuleString.removeStar( content )));
    }
}
