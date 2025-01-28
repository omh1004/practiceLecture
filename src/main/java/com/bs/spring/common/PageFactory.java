package com.bs.spring.common;

public class PageFactory {
    public static String pageBar(
            int cPage
            , int numPerPage
            ,int totalData
            ,String url) {
        StringBuffer sb=new StringBuffer();
//        int totalPage=(int)Math.ceil((double)totalData/numPerPage);
//        int pageBarSize=5;
//        int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
//        int pageEnd=pageNo+pageBarSize-1;

        //전체페이지는 (전체데이터수/페이지당 데이터수) 이고, 이때, 나누기로 하면, 나머지가 짤리므로,
        //double을 통해 소수점까지 나오게 한후에 올림 처리해서 유실되는 데이터가 없도록 한다.
        //페이징시 필요한 데이터 , 전체페이지, 시작페이지, 페이지당 데이터 , 끝페이지

        int totalPage = (int)Math.ceil((double)totalData/numPerPage);
        int pageBarSize = 5;
        int pageNo = ((cPage -1)/pageBarSize)*pageBarSize+1;
        int pageEnd = pageNo+pageBarSize-1;

        //sb.append("<ul class='pagination justify-content-center pagination-sm'>");
        //ul 태그를 만든 후에 , 부트스트랩 속성클래스인 pagination ,justify-content-center pagination-sm을 준다.
        sb.append("<ul class='pagenation justify-content-center pagination-sm'>");

        //pageNo가 1페이지 일떄
        //이전페이지가 없으니 , disabled처리한다.

//
//        if(pageNo==1){
//            sb.append("<li class='page-item disabled'>");
//            sb.append("<a class='page-link' href='#'>이전</a>");
//            sb.append("</li>");
//        }else{
//            sb.append("<li class='page-item'>");
//            sb.append("<a class='page-link' href='javascript:fn_paging("+(pageNo-1)+")'>이전</a>");
//            sb.append("</li>");
//        }
        if(pageNo==1){
            sb.append("<li class='page-item disabled'>");
            sb.append("<a class='page-link' href='#'>이전</a>");
            sb.append("</li>");
        }else{
            sb.append("<li class='page-item>");
            sb.append("<a class='page-link' href='javascript:fn_paging("+(pageNo-1)+")'>이전</a>");
            sb.append("</li>");
        }

        while(!(pageNo>pageEnd||pageNo>totalPage)){
            if(cPage==pageNo){
                sb.append("<li class='page-item disabled'>");
                sb.append("<a class='page-link' href='#'>"+pageNo+"</a>");
                sb.append("</li>");
            }else{
                sb.append("<li class='page-item'>");
                sb.append("<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>"+pageNo+"</a>");
                sb.append("</li>");
            }
            pageNo++;
        }
        if(pageNo>totalPage){
            sb.append("<li class='page-item disabled'>");
            sb.append("<a class='page-link' href='#'>다음</a>");
            sb.append("</li>");
        }else{
            sb.append("<li class='page-item'>");
            sb.append("<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>다음</a>");
            sb.append("</li>");
        }
        sb.append("</ul>");
        sb.append("<script>");
        sb.append("function fn_paging(pageNo){");
        sb.append("location.assign('"+url+"?cPage='+pageNo+'&numPerPage="+numPerPage+"');");
        sb.append("}");
        sb.append("</script>");

        return sb.toString();

    }
}


