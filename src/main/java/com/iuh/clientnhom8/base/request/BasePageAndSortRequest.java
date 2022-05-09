package com.iuh.clientnhom8.base.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasePageAndSortRequest extends BasePageRequest {
    private List<BaseSort> sort;
}
