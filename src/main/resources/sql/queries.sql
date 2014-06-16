-- группа с связью (для конкретной книги)
SELECT
  NAME,
  DESCRIPTION,
  GROUP_ID,
  BOOK_ID
FROM
  BOOK_GROUP
  INNER JOIN
  (SELECT
     *
   FROM GROUP_BOOK_CONNECTION
   WHERE BOOK_ID = 1) AS tbl1
    ON BOOK_GROUP.ID = tbl1.GROUP_ID;

-- вся инфа по книге + группы
SELECT
  *
FROM
  BOOK
  INNER JOIN
  (SELECT
     NAME,
     DESCRIPTION,
     GROUP_ID,
     BOOK_ID
   FROM
     BOOK_GROUP
     INNER JOIN
     (SELECT
        *
      FROM GROUP_BOOK_CONNECTION
      WHERE BOOK_ID = 1) AS tbl1
       ON BOOK_GROUP.ID = tbl1.GROUP_ID) AS tbl2
    ON book.ID = tbl2.BOOK_ID;

-- все группы с списками книг
SELECT
  tbl2.GROUP_ID,
  tbl2.NAME as GROUP_NAME,
  tbl2.DESCRIPTION as GROUP_DESCRIPTION,
  tbl2.BOOK_ID,
  BOOK.NAME,
  BOOK.AUTHOR,
  BOOK.DESCRIPTION,
  BOOK.CREATE_DATE
FROM
  BOOK
  INNER JOIN
  (SELECT
     NAME,
     DESCRIPTION,
     GROUP_ID,
     BOOK_ID
   FROM
     BOOK_GROUP
     INNER JOIN
     GROUP_BOOK_CONNECTION
       ON BOOK_GROUP.ID = GROUP_BOOK_CONNECTION.GROUP_ID) AS tbl2
    ON book.ID = tbl2.BOOK_ID ORDER BY group_id;

-- выбирает книги и группы для конкретной кники - т.е. те книги, что находятся в смежных группах
SELECT
  *
FROM
  GROUP_BOOK_CONNECTION
WHERE GROUP_BOOK_CONNECTION.GROUP_ID IN
      (SELECT
         GROUP_ID
       FROM
         GROUP_BOOK_CONNECTION
       WHERE GROUP_BOOK_CONNECTION.BOOK_ID = 1);


-- выбирает книги и группы для конкретной кники - т.е. те книги, что находятся в смежных группах (полная информация)
SELECT
  tbl2.GROUP_ID,
  tbl2.NAME,
  tbl2.DESCRIPTION,
  tbl2.BOOK_ID,
  BOOK.NAME,
  BOOK.DESCRIPTION,
  BOOK.AUTHOR,
  BOOK.CREATE_DATE
FROM
  BOOK
  INNER JOIN

  (SELECT
     GROUP_ID,
     NAME,
     DESCRIPTION,
     BOOK_ID
   FROM
     BOOK_GROUP
     INNER JOIN
     (SELECT
        *
      FROM
        GROUP_BOOK_CONNECTION
      WHERE GROUP_BOOK_CONNECTION.GROUP_ID IN
            (SELECT
               GROUP_ID
             FROM
               GROUP_BOOK_CONNECTION
             WHERE GROUP_BOOK_CONNECTION.BOOK_ID = 1)) AS tbl1
       ON BOOK_GROUP.ID = tbl1.GROUP_ID) AS tbl2
    ON book.ID = tbl2.BOOK_ID ORDER BY group_id;



-- выбрать конкретную группу с книгами
SELECT
  tbl2.GROUP_ID,
  tbl2.NAME as GROUP_NAME,
  tbl2.DESCRIPTION as GROUP_DESCRIPTION,
  tbl2.BOOK_ID,
  BOOK.NAME,
  BOOK.AUTHOR,
  BOOK.DESCRIPTION,
  BOOK.CREATE_DATE
FROM
  BOOK
  INNER JOIN
  (SELECT
     NAME,
     DESCRIPTION,
     GROUP_ID,
     BOOK_ID
   FROM
     BOOK_GROUP
     INNER JOIN
     GROUP_BOOK_CONNECTION
       ON BOOK_GROUP.ID = GROUP_BOOK_CONNECTION.GROUP_ID) AS tbl2
    ON book.ID = tbl2.BOOK_ID WHERE GROUP_ID = 1 ORDER BY group_id;