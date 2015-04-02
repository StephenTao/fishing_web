package com.augmentum.fishing.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.augmentum.common.util.AppUtil;

public class PaginationDTO<T> {
    private static final String KEY_PAGE_SIZE = "pagination.pageSize";

    private Integer totalRowCount;
    private Integer pageSize;
    private Integer pageCount;
    private Integer currentPage;
    private Integer offset;
    private Integer rowCount;
    private List<T> itemList;
    private Map<String, Object> parameterMap;
    private String relativeUrl;
    private Boolean lastPage;

    public Integer getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(Integer totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    /**
     * Get page size set by frontend. If it is not set, then get default page
     * size from application configuration file.
     *
     * @return
     */
    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1) {
            String size = AppUtil.getPropertyValue(KEY_PAGE_SIZE);
            pageSize = Integer.parseInt(size);
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        if (totalRowCount == null || totalRowCount < 1) {
            pageCount = 0;
            return pageCount;
        }
        pageCount = (totalRowCount - 1) / getPageSize() + 1;
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurrentPage() {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Get the start position for MySQL limit selection.
     *
     * @return
     */
    public Integer getOffset() {
        offset = (getCurrentPage() - 1) * getPageSize();
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getRowCount() {
        if (rowCount == null || rowCount < 1) {
            rowCount = getPageSize();
        }
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
    }

    public Map<String, Object> getParameterMap() {
        if (parameterMap == null) {
            parameterMap = new HashMap<String, Object>();
        }
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public void setRelativeUrl(String relativeUrl) {
        this.relativeUrl = relativeUrl;
    }

    public Boolean getLastPage() {
        this.lastPage = ((getCurrentPage().intValue() == getPageCount().intValue()) || (getPageCount() == 0));
        return this.lastPage;
    }

    public void setLastPage(Boolean lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PaginationDTO [totalRowCount=");
        builder.append(totalRowCount);
        builder.append(", pageSize=");
        builder.append(pageSize);
        builder.append(", pageCount=");
        builder.append(pageCount);
        builder.append(", currentPage=");
        builder.append(currentPage);
        builder.append(", offset=");
        builder.append(offset);
        builder.append(", rowCount=");
        builder.append(rowCount);
        builder.append(", itemList=");
        builder.append(itemList);
        builder.append(", parameterMap=");
        builder.append(parameterMap);
        builder.append(", relativeUrl=");
        builder.append(relativeUrl);
        builder.append(", lastPage=");
        builder.append(lastPage);
        builder.append("]");
        return builder.toString();
    }

}
