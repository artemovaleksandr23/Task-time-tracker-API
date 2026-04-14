CREATE TABLE IF NOT EXISTS task (
                      id BIGSERIAL PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description TEXT,
                      status VARCHAR(20) NOT NULL DEFAULT 'NEW'
);

CREATE TABLE IF NOT EXISTS time_record (
                             id BIGSERIAL PRIMARY KEY,
                             employee_id BIGINT NOT NULL,
                             task_id BIGINT NOT NULL,
                             start_time TIMESTAMP NOT NULL,
                             end_time TIMESTAMP,
                             description TEXT,

                             CONSTRAINT fk_task
                                 FOREIGN KEY (task_id)
                                     REFERENCES task(id)
                                     ON DELETE CASCADE
);