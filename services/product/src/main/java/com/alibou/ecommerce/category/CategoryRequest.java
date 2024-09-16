package com.alibou.ecommerce.category;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(Integer id, @NotNull(message = "name can't be Null") String name, String description) {
}
