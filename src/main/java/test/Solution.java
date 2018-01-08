package test;

import system.model.TestEntity;
import system.model.helper.Helper;

/**
 * Created by vladimir on 03.01.2018.
 */
public class Solution {
    public static void main(String[] args) {
        TestEntity entity = getTestEntity();
        Helper.toLine(entity);
    }

    private static TestEntity getTestEntity() {
        TestEntity entity = new TestEntity();
        entity.setId(10);
        entity.setTitle("Title");
        entity.setDescription("Description");
        entity.setAuthor("Author");
        entity.setIsbn("ISBN");
        entity.setPrintYear(2018);
        entity.setReadAlready((byte)1);
        return entity;
    }
}
