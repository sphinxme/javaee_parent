package com.dosx.javase.service.workflow.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MinUser
{
    Long id;
    String name;
}
