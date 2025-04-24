CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP table IF exists "users" CASCADE;
CREATE TABLE "users" (
                         "id" uuid not null PRIMARY KEY DEFAULT uuid_generate_v4(),
                         "username" varchar(30) unique not NULL,
                         "email" varchar(100) unique not NULL,
                         "is_verified" boolean default false,
                         "phone_number" varchar(20) unique NULL,
                         "first_name" varchar(60) not null,
                         "last_name" varchar(60) not null,
                         "date_of_birth" timestamp not NULL,
                         "gender" varchar(50) not NULL,
                         "role" varchar(50) not NULL,
                         "avatar" varchar(100),
                         "address" varchar(500),
                         "deleted_at" timestamp default NULL,
                         "updated_at" timestamp not null default CURRENT_TIMESTAMP,
                         "created_at" timestamp not null default CURRENT_TIMESTAMP
);

drop table if exists "auths" cascade;
create table "auths" (
                         "user_id" uuid not null PRIMARY KEY,
                         "salt" text not null,
                         "password" text not null,
                         "updated_at" timestamp default CURRENT_TIMESTAMP,
                         "created_at" timestamp default CURRENT_TIMESTAMP,
                         "deleted_at" timestamp default null,

                         constraint fk_user_id foreign key (user_id) references users(id) on delete cascade
);

drop table if exists "learner_info";
create table "learner_info" (
                                "id" uuid not null PRIMARY KEY DEFAULT uuid_generate_v4(),
                                "user_id" uuid not null,
                                "type" varchar(100),
                                "grade" int,
                                "school" varchar(300),
                                "deleted_at" timestamp default NULL,
                                "updated_at" timestamp not null default CURRENT_TIMESTAMP,
                                "created_at" timestamp not null default CURRENT_TIMESTAMP,

                                constraint fk_user_id foreign key (user_id) references users(id) on delete cascade
);

drop table if exists "teacher_infos" cascade;
create table teacher_infos (
                               "id" uuid not null PRIMARY KEY DEFAULT uuid_generate_v4(),
                               "user_id" uuid not null,
                               "biography" text not null,
                               "edu_qualification" varchar(500) not null,
                               "updated_at" timestamp default CURRENT_TIMESTAMP,
                               "created_at" timestamp default CURRENT_TIMESTAMP,
                               "deleted_at" timestamp default null,
                               CONSTRAINT fk_teacher_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE cascade
);

DROP table IF exists "subjects" CASCADE;
CREATE TABLE "subjects" (
                            "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                            "name" varchar(50) not null,
                            "description" varchar(500),
                            "thumbnail_url" varchar(300),
                            "deleted_at" timestamp default NULL,
                            "created_at" timestamp default CURRENT_TIMESTAMP,
                            "updated_at" timestamp default CURRENT_TIMESTAMP
);

insert into subjects ("id","name", "description","thumbnail_url")
values (default, 'English', 'English', 'thumbnail');

DROP table IF exists "courses" CASCADE;
CREATE TABLE "courses" (
                           "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                           "name" varchar(200) not NULL,
                           "description" text not NULL,
                           "background_img" text,
                           "thumbnail" text,
                           "start_date" timestamp,
                           "end_date" timestamp,
                           "price" decimal(12,2),
                           "level" varchar(50) not NULL,
                           "is_verified" boolean default false,
                           "subject_id" uuid not NULL,
                           "grade" int,
                           "code" varchar(10) not NULL,
                           "teacher_id" uuid references users(id) on delete cascade,
                           "updated_at" timestamp default CURRENT_TIMESTAMP,
                           "created_at" timestamp default CURRENT_TIMESTAMP,
                           "deleted_at" timestamp default null,
                           FOREIGN KEY ("subject_id") REFERENCES "subjects" ("id")
);

drop table if exists "user_enroll_course" cascade;
create table "user_enroll_course" (
                                      "id" uuid not NULL DEFAULT uuid_generate_v4(),
                                      "user_id" uuid not NULL,
                                      "course_id" uuid not null,
                                      "price" decimal(12,2),
                                      "enroll_status" varchar(50) not null,
                                      "student_id" int,
                                      "updated_at" timestamp default CURRENT_TIMESTAMP,
                                      "created_at" timestamp default CURRENT_TIMESTAMP,
                                      "deleted_at" timestamp default null,
                                      PRIMARY KEY ("user_id", "course_id"),
                                      FOREIGN KEY ("user_id") REFERENCES "users" ("id"),
                                      FOREIGN KEY ("course_id") REFERENCES "courses" ("id")
);

DROP table IF exists "course_infos" CASCADE;
CREATE TABLE "course_infos" (
                                "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                                "course_id" uuid not NULL references courses(id) on delete cascade,
                                "content" text not NULL,
                                "type" varchar(100) not NULL,
                                "deleted_at" timestamp default NULL,
                                "created_at" timestamp default CURRENT_TIMESTAMP,
                                "updated_at" timestamp default CURRENT_TIMESTAMP
);

DROP table IF exists "sections" CASCADE;
CREATE TABLE "sections" (
                            "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                            "name" varchar(100) not NULL,
                            "description" text not NULL,
                            "course_id" uuid not null references courses(id) on delete CASCADE,
                            "deleted_at" timestamp default NULL,
                            "created_at" timestamp default CURRENT_TIMESTAMP,
                            "updated_at" timestamp default CURRENT_TIMESTAMP
);

DROP table IF exists "lessons" CASCADE;
CREATE TABLE "lessons" (
                           "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                           "name" varchar(100) not NULL,
                           "description" text not NULL,
                           "background" varchar(100),
                           "video_url" varchar(100),
                           "section_id" uuid references sections(id) on delete cascade,
                           "updated_at" timestamp default CURRENT_TIMESTAMP,
                           "created_at" timestamp default CURRENT_TIMESTAMP,
                           "deleted_at" timestamp default NULL
);

DROP table IF exists "resources" CASCADE;
CREATE TABLE "resources" (
                             "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                             "title" varchar(200) not NULL,
                             "url" varchar(200) not null,
                             "description" text not null,
                             "owner_id" uuid references users(id) on delete cascade,
                             "updated_at" timestamp default CURRENT_TIMESTAMP,
                             "created_at" timestamp default CURRENT_TIMESTAMP,
                             "deleted_at" timestamp default NULL
);

DROP table IF exists "document_placement" CASCADE;
CREATE TABLE document_placement (
                                    "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                                    "document_id" uuid NOT NULL REFERENCES resources(id) ON DELETE CASCADE,
                                    "course_id" uuid references courses(id) on delete cascade,
                                    "lesson_id" uuid references lessons(id) on delete cascade,
                                    "deleted_at" timestamp default NULL,
                                    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

DROP table IF exists "assignments" CASCADE;
CREATE TABLE "assignments" (
                               "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                               "title" varchar(500) not null,
                               "description" text not null,
                               "total_point" integer not null,
                               "is_multiple_attempt" boolean,
                               "type" varchar(100) not null,
                               "start_time" timestamp,
                               "end_time" timestamp,
                               "time_limit_second" integer,
                               "lesson_id" uuid not null references lessons(id),
                               "subject_id" uuid references subjects(id),
                               "teacher_id" uuid references users(id),
                               "deleted_at" timestamp default NULL,
                               "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                               "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

DROP table IF exists "assignment_attempts" CASCADE;
CREATE TABLE "assignment_attempts" (
                                       "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                                       "assignment_id" uuid not null references assignments(id),
                                       "user_id" uuid not null references users(id),
                                       "point" integer,
                                       "teacher_comment" text,
                                       "finished_at" timestamp default NULL,
                                       "deleted_at" timestamp default NULL,
                                       "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                       "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

DROP table IF exists "questions" CASCADE;
CREATE TABLE "questions" (
                             "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                             "parent_id" uuid references questions(id),
                             "assignment_id" uuid references assignments(id),
                             "title" text not null,
                             "image_url" varchar(500),
                             "audio_url" varchar(500),
                             "point" integer,
                             "type" varchar(100) not NULL,
                             "level" varchar(50) not NULL,
                             "answer_explain" text,
                             "teacher_id" uuid references users(id),
                             "subject_id" uuid references subjects(id),
                             "deleted_at" timestamp default NULL,
                             "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                             "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

DROP table IF exists "question_choices" CASCADE;
CREATE TABLE "question_choices" (
                                    "id" uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                                    "content" text not null,
                                    "order" int not null,
                                    "is_correct" boolean not null,
                                    "question_id" uuid not null references questions(id),
                                    "deleted_at" timestamp default NULL,
                                    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

DROP table IF exists "question_answers" CASCADE;
create table "question_answers" (
                                    id uuid not NULL PRIMARY key DEFAULT uuid_generate_v4(),
                                    user_id uuid not null references users(id),
                                    question_id uuid not null references questions(id),
                                    assignment_attempt_id uuid not null references assignment_attempts("id"),
                                    selected_option_id uuid references question_choices(id),
                                    text_answer text,
                                    score int,
                                    "deleted_at" timestamp default NULL,
                                    "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                    "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);
alter table question_answers  add constraint unique_question_attempt_user
    UNIQUE (question_id, assignment_attempt_id, user_id);

-- feedback for long answer
DROP table IF exists "feedbacks" CASCADE;
CREATE TABLE "feedbacks" (
                             "id" uuid not null primary key,
                             "message" text not null,
                             "user_id" uuid not null references users(id),
                             "type" varchar(100) not null,
                             "question_answer_id" uuid  not null references question_answers(id),
                             "feedback_id" uuid references feedbacks(id) default NULL, -- target feedback
                             "deleted_at" timestamp default NULL,
                             "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                             "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

drop table if exists "correct_answers" cascade;
create table "correct_answers" (
                                   question_id uuid not NULL PRIMARY key references questions("id") on delete cascade DEFAULT uuid_generate_v4(),
                                   correct_text_answer text,
                                   answer_explain text,
                                   "deleted_at" timestamp default NULL,
                                   "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                   "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

--End question

DROP table IF exists "conversations" CASCADE;
CREATE TABLE "conversations" (
                                 "id" uuid not null primary key DEFAULT uuid_generate_v4(),
                                 "content" text not null,
                                 "owner_id" uuid not null references users(id) on delete cascade,
                                 "placement_id" uuid,
                                 "deleted_at" timestamp default NULL,
                                 "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                                 "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

DROP table IF exists "comments" CASCADE;
CREATE TABLE "comments" (
                            "id" uuid not null primary key default uuid_generate_v4(),
                            "message" text not null,
                            "user_id" uuid not null references users(id),
                            "conversation_id" uuid  not null references conversations(id),
                            "deleted_at" timestamp default NULL,
                            "created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
                            "updated_at" timestamp DEFAULT CURRENT_TIMESTAMP
);

drop table if exists "reacts" cascade;
create table "reacts" (
                          "id" uuid not null primary key default uuid_generate_v4(),
                          "text" varchar(50) not null,
                          deleted_at TIMESTAMP DEFAULT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP table IF exists "react_conversations" CASCADE;
create table "react_conversations" (
                                       user_id uuid not null default uuid_generate_v4(),
                                       conversation_id uuid not null,
                                       type uuid not null, -- The type of reaction
                                       deleted_at TIMESTAMP DEFAULT NULL,
                                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       foreign key ("type") references "reacts" ("id"),
                                       foreign key ("user_id") references "users" ("id"),
                                       foreign key ("conversation_id") references "conversations" ("id"),
                                       primary key ("user_id", "conversation_id")
);

DROP table IF exists "react_comments" CASCADE;
create table "react_comments" (
                                  user_id uuid not null primary key default uuid_generate_v4(),
                                  comment_id uuid not null,
                                  type uuid not null, -- The type of reaction
                                  deleted_at TIMESTAMP DEFAULT NULL,
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  foreign key ("type") references "reacts" ("id"),
                                  foreign key ("user_id") references "users" ("id"),
                                  foreign key ("comment_id") references "comments" ("id")
);