package com.newadmin.demogenerator.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import java.io.Serial;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Sort;

/**
 * 分页查询条件
 *
 * @author couei
 * @author Charles7c
 * @since 1.0.0
 */
@ParameterObject
@Schema(description = "分页查询条件")
public class PageQuery extends SortQuery {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 默认页码：1
     */
    private static final int DEFAULT_PAGE = 1;
    /**
     * 默认每页条数：10
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * 页码
     */
    @Schema(description = "页码", example = "1")
    @Min(value = 1, message = "页码最小值为 {value}")
    private Integer currentPage = DEFAULT_PAGE;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数", example = "10")
    private Integer pageSize = DEFAULT_SIZE;

    /**
     * 基于分页查询条件转换为 MyBatis Plus 分页条件
     *
     * @param <T> 列表数据类型
     * @return MyBatis Plus 分页条件
     */
    public <T> IPage<T> toPage() {
        Page<T> mybatisPage = new Page<>(this.getCurrentPage(), this.getPageSize());
        Sort pageSort = this.getSort();
        if (CollUtil.isNotEmpty(pageSort)) {
            for (Sort.Order order : pageSort) {
                OrderItem orderItem = new OrderItem();
                orderItem.setAsc(order.isAscending());
                orderItem.setColumn(CharSequenceUtil.toUnderlineCase(order.getProperty()));
                mybatisPage.addOrder(orderItem);
            }
        }
        return mybatisPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer page) {
        this.currentPage = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer size) {
        this.pageSize = size;
    }
}
