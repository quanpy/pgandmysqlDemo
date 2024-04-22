JSONB 支持 JSON 数据索引。这使 JSONB 在根据 JSON 列中的 key 或属性查询数据时具有显著优势。

可以对 JSON 列应用各种类型的索引，包括 GIN、HASH 和 BTREE。GIN 适用于复杂数据结构的索引，包括数组和 JSON。当处理 < 和 >= 等范围运算符时，BTREE 可以实现高效查询。

例如，如果我们经常需要根据 address 中的 postCode 属性检索数据，那么可以创建以下索引：

```sql
CREATE INDEX idx_postcode ON student USING B-Tree((address->'postCode'));
```


总结来说，B-Tree 索引 是最通用且广泛使用的索引类型，适用于大多数查询场景，尤其是涉及范围查询和排序的情况。Hash 索引 虽然理论上在等值查询上可能有优势，但实践中并不推荐使用，因其局限性和维护成本。GIN 索引 特别适合处理多值属性和全文搜索需求，提供了对复杂查询的强大支持，但可能带来更大的存储和更新开销。在选择索引类型时，应根据实际数据模型、查询模式和性能要求来做出最佳决策。

reference: https://springdoc.cn/spring-boot-jpa-storing-postgresql-jsonb/


### 操作数据库就不要用 虚拟线程了！！ tomcat nio 效率够啦