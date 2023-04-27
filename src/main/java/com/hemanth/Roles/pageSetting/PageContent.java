package com.hemanth.Roles.pageSetting;

import com.hemanth.Roles.dto.RolesDto;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageContent
{
    List<RolesDto> content;
    int pageNo;

    int pageSize;

    long total_Element;

}
