INSERT INTO exceptional_leaves (id, name, legal_balance, granted_days)
VALUES (gen_random_uuid(), 'MARRIAGE', 4, 4),
       (gen_random_uuid(), 'BIRTH', 3, 3),
       (gen_random_uuid(), 'BEREAVEMENT_FIRST_DEGREE', 3, 3),
       (gen_random_uuid(), 'BEREAVEMENT_SECOND_DEGREE', 2, 2),
       (gen_random_uuid(), 'MOVING', 2, 2),
       (gen_random_uuid(), 'SICK_CHILD', 2, 2),
       (gen_random_uuid(), 'PATERNITY', 3, 3);