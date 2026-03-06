# SELECT 
#     CASE 
#         WHEN (
#             (SELECT COUNT(*)
#             FROM DEVELOPERS D
#             WHERE EXISTS (
#                 SELECT 1 
#                 FROM SKILLCODES S
#                 WHERE S.CATEGORY = 'Front End'
#                   AND (D.SKILL_CODE & S.CODE) > 0) > 0
#             )) AND 
#             (SELECT COUNT(*)
#             FROM DEVELOPERS D
#             WHERE EXISTS (
#                 SELECT 1 
#                 FROM SKILLCODES S
#                 WHERE S.CATEGORY = 'Front End'
#                   AND (D.SKILL_CODE & S.CODE) > 0) > 0
#             )
            
#         )THEN 'A' 
#         ELSE 'B'
#     END AS GRADE, 
#     D.ID, 
#     D.EMAIL
# FROM SKILLCODES S
# JOIN DEVELOPERS D 
#   ON S.CODE & D.SKILL_CODE > 0
# WHERE S.CATEGORY = 'Front End' OR S.NAME = 'Python' OR S.NAME = 'C#'

WITH FRONT AS (
    SELECT ID
    FROM DEVELOPERS D
    WHERE EXISTS (
        SELECT 1 
        FROM SKILLCODES S
        WHERE S.CATEGORY = 'Front End'
          AND (D.SKILL_CODE & S.CODE) > 0
    ) 
),
C_SHARP AS (
    SELECT ID
    FROM DEVELOPERS D
    WHERE EXISTS (
        SELECT 1 
        FROM SKILLCODES S
        WHERE S.NAME = 'C#'
          AND (D.SKILL_CODE & S.CODE) > 0
    ) 
),
PYTHON AS (
    SELECT ID
    FROM DEVELOPERS D
    WHERE EXISTS (
        SELECT 1 
        FROM SKILLCODES S
        WHERE S.NAME = 'Python'
          AND (D.SKILL_CODE & S.CODE) > 0
    ) 
)

SELECT 
    CASE 
        WHEN (
            EXISTS(SELECT 1 FROM FRONT F WHERE F.ID = D.ID) 
            AND 
            EXISTS(SELECT 1 FROM PYTHON P WHERE P.ID = D.ID)
        ) THEN 'A'
        WHEN (
            EXISTS(SELECT 1 FROM C_SHARP C WHERE C.ID = D.ID)
        ) THEN 'B'
        ELSE 'C'
    END AS GRADE,
    D.ID,
    D.EMAIL
FROM DEVELOPERS D
WHERE EXISTS(SELECT 1 FROM FRONT F WHERE F.ID = D.ID) AND EXISTS(SELECT 1 FROM PYTHON P WHERE P.ID = D.ID)
   OR EXISTS(SELECT 1 FROM C_SHARP C WHERE C.ID = D.ID)
   OR EXISTS(SELECT 1 FROM FRONT F WHERE F.ID = D.ID)
ORDER BY GRADE ASC, D.ID ASC;

