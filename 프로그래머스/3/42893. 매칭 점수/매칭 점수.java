import java.util.*;

class Solution {
    double maxScore = -1;
   	int minIdx = Integer.MAX_VALUE; 
    
    Map<String, List<Integer>> graph;
    
   	class Page {
        String domain; 
        int basicScore, linkCnt;
        double linkScore;
        double matchScore; 
        
        public Page(String domain, int basicScore, int linkCnt, double linkScore) {
            this.domain = domain;
            this.basicScore = basicScore;
            this.linkCnt = linkCnt;
            this.linkScore = linkScore;
        }
    } 
    
    public int solution(String word, String[] pages) {
        graph = new HashMap<>(); 

        int pageLen = pages.length;
       	Page[] pageArr = new Page[pageLen];  
                
        for (int i = 0; i < pageLen; i++) {
            // 바디 안의 텍스트에 대해 기본 점수 구하기 
            String bodyText = extractBodyText(pages[i]);
            int basicScore = getBasicScore(bodyText, word);
            
            // a태그에 대해 외부 링크 연결 상태 구하기
            String domain = extractDomain(pages[i]);
            int linkCnt = 0;
            int searchStart = 0;
            while (true) {
                int aTagStart = pages[i].indexOf("<a ", searchStart);
                if (aTagStart == -1) break; 
                
                String externalUrl = extractHref(pages[i], aTagStart);
                if (externalUrl != null) {
                    graph.putIfAbsent(externalUrl, new ArrayList<>());
                    graph.get(externalUrl).add(i);
                    linkCnt++;
                }
                searchStart = aTagStart + 1;
            }
            
            // 링크 점수 
            double linkScore = (linkCnt == 0) ? 0 : (double) basicScore / linkCnt;
            pageArr[i] = new Page(domain, basicScore, linkCnt, linkScore);
            // System.out.println(pageArr[i].linkScore);
        }
        
        /*
        for (String key: graph.keySet()) {
            System.out.print(key + ": ");
            for (int num: graph.get(key)) {
            	System.out.print(num + " ");
            }
            System.out.println();
        }
        */
        
        // 모든 페이지 url에 대해서 링크 가능한 모든 페이지를 확인해서 매칭 점수를 출력한다.
        for (int i = 0; i < pageLen; i++) {
            String domain = pageArr[i].domain;
            double matchScore = pageArr[i].basicScore; 
            if (graph.containsKey(domain)) {
                for (int next: graph.get(domain)) {
                    double linkScore = pageArr[next].linkScore; 
                    matchScore += linkScore; 
                }
            }
            pageArr[i].matchScore = matchScore; 
            // System.out.println(pageArr[i].matchScore);
        }
        
        for (int i = 0; i < pageLen; i++) {
            if (maxScore < pageArr[i].matchScore) {
                maxScore = pageArr[i].matchScore;
                minIdx = i;
            }
        }
        
        return minIdx;
    }
        
    private String extractHref(String html, int aTagStart) {
        int hrefStart = html.indexOf("href=\"", aTagStart);
        if (hrefStart == -1) return null; 
        
        int aTagEnd = html.indexOf(">", aTagStart); 
        if (hrefStart > aTagEnd) return null; 
        
        hrefStart += "href=\"".length();
        int hrefEnd = html.indexOf("\"", hrefStart); 
        
        return html.substring(hrefStart, hrefEnd);                                  
    }
    
    private String extractDomain(String html) {
        int metaStart = html.indexOf("<meta property=\"og:url\"");
        if (metaStart == -1) return null; 
        
        int contentStart = html.indexOf("content=\"", metaStart);
        if (contentStart == -1) return null;
        
        contentStart += "content=\"".length();
        int contentEnd = html.indexOf("\"", contentStart);
        
        return html.substring(contentStart, contentEnd);
    }
    
    private String extractBodyText(String text) {
        int bodyStart = text.indexOf("<body>");
        int bodyEnd = text.indexOf("</body>");
        
        bodyStart += "<body>".length();
        return text.substring(bodyStart, bodyEnd);
    }
    
    private String extractAttribute(String text, String attr) {
        String key = attr + "=\"";
        
        int start = text.indexOf(key);
        if (start == -1) return null; 
       
        start += key.length();
        int end = text.indexOf("\"", start); 
        
       	return text.substring(start, end);
    }
        
    private int getBasicScore(String text, String target) {
		text = text.toLowerCase();
        target = target.toLowerCase();
        
       	int textLen = text.length(); 
        int targetLen = target.length(); 
       
       	int score = 0;
        for (int i = 0; i <= textLen - targetLen; i++) {
            if (text.substring(i, i + targetLen).equals(target)) {
               	boolean leftOk = (i == 0) || (!Character.isLetter(text.charAt(i - 1)));
               	boolean rightOk = (i + targetLen == textLen) || (!Character.isLetter(text.charAt(i + targetLen)));
           
                if (leftOk && rightOk) {
                    score++;
                }
            }
        }
        
        return score;
    }
}