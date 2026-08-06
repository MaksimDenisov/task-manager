TRUNCATE TABLE tasks, users RESTART IDENTITY CASCADE;

INSERT INTO users (email, password)
VALUES
    ('alex.morozov@example.com', 'hash password'),
    ('maria.volkova@example.com', 'hash password'),
    ('ivan.petrov@example.com', 'hash password'),
    ('elena.sokolova@example.com', 'hash password'),
    ('dmitry.ivanov@example.com', 'hash password'),
    ('anna.kuznetsova@example.com', 'hash password'),
    ('sergey.orlov@example.com', 'hash password'),
    ('olga.fedorova@example.com', 'hash password'),
    ('nikita.smirnov@example.com', 'hash password'),
    ( 'kate.novikova@example.com', 'hash password');

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
