TRUNCATE TABLE tasks, users RESTART IDENTITY CASCADE;

INSERT INTO users (id, email, password)
VALUES
    (1, 'alex.morozov@example.com', 'hash password'),
    (2, 'maria.volkova@example.com', 'hash password'),
    (3, 'ivan.petrov@example.com', 'hash password'),
    (4, 'elena.sokolova@example.com', 'hash password'),
    (5, 'dmitry.ivanov@example.com', 'hash password'),
    (6, 'anna.kuznetsova@example.com', 'hash password'),
    (7, 'sergey.orlov@example.com', 'hash password'),
    (8, 'olga.fedorova@example.com', 'hash password'),
    (9, 'nikita.smirnov@example.com', 'hash password'),
    (10, 'kate.novikova@example.com', 'hash password');

INSERT INTO tasks (name, description, is_done, user_id, completed_at)
SELECT
    'Task ' || task_num || ' for user ' || user_id,
    'Generated description for task ' || task_num,
    CASE WHEN task_num % 3 = 0 THEN false ELSE true END,
    user_id,
    CASE
        WHEN task_num % 3 = 0 THEN NULL
        ELSE NOW() - (task_num * user_id || ' days')::interval
        END
FROM generate_series(1, 10) AS user_id,
     generate_series(1, 10) AS task_num;
