INSERT INTO category (title, status, created_at, updated_at) VALUES
                                                                 ('한식', 'ACTIVATED', NOW(), NOW()),
                                                                 ('중식', 'ACTIVATED', NOW(), NOW()),
                                                                 ('일식', 'ACTIVATED', NOW(), NOW()),
                                                                 ('양식', 'ACTIVATED', NOW(), NOW()),
                                                                 ('디저트', 'ACTIVATED', NOW(), NOW()),
                                                                 ('베이킹', 'ACTIVATED', NOW(), NOW()),
                                                                 ('샐러드', 'ACTIVATED', NOW(), NOW()),
                                                                 ('스프', 'ACTIVATED', NOW(), NOW()),
                                                                 ('면요리', 'ACTIVATED', NOW(), NOW()),
                                                                 ('밥요리', 'ACTIVATED', NOW(), NOW()),
                                                                 ('고기요리', 'ACTIVATED', NOW(), NOW()),
                                                                 ('해산물', 'ACTIVATED', NOW(), NOW()),
                                                                 ('채식요리', 'ACTIVATED', NOW(), NOW()),
                                                                 ('간편요리', 'ACTIVATED', NOW(), NOW()),
                                                                 ('세계요리', 'ACTIVATED', NOW(), NOW());

INSERT INTO post (
    title,
    content,
    created_at,
    updated_at,
    viewed_cnt,
    visibility,
    status,
    category_id
) VALUES

-- category 1 (한식)
('김치찌개 레시피', '맛있는 김치찌개 끓이는 방법', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 1),
('된장찌개 레시피', '구수한 된장찌개 만드는 법', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 1),

-- category 2 (중식)
('짜장면 만들기', '집에서 만드는 짜장면', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 2),
('볶음밥 레시피', '중식 스타일 볶음밥', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 2),

-- category 3 (일식)
('초밥 만들기', '초밥 밥 짓는 방법', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 3),
('라멘 레시피', '집에서 만드는 라멘', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 3),

-- category 4 (양식)
('파스타 만들기', '토마토 파스타 레시피', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 4),
('스테이크 굽기', '완벽한 스테이크 굽는 법', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 4);

-- category 5 (디저트)
('요거트 맛있게 먹는법', '딸기잼을 넣어 먹는다.', '레시피', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 5),
('식빵 먹는방법', '딸기잼, 땅콩잼 바르고 계란후라이 하나 구워서 올려드세요.', NOW(), NOW(), 0, 'PUBLIC', 'ACTIVATED', 5);

INSERT INTO comment (content, status, created_at, parent_id, post_id) VALUES

-- =========================
-- post 1 (김치찌개)
-- =========================
('김치찌개 진짜 맛있어요!', 'ACTIVATED', NOW(), NULL, 1),
('레시피대로 하니 성공했어요', 'ACTIVATED', NOW(), NULL, 1),
('고기 추가하면 더 맛있어요', 'ACTIVATED', NOW(), NULL, 1),
('국물이 너무 좋아요', 'ACTIVATED', NOW(), NULL, 1),
('밥도둑이네요', 'ACTIVATED', NOW(), NULL, 1),
('신김치로 해야 하나요?', 'ACTIVATED', NOW(), NULL, 1),
('두부 넣어도 되나요?', 'ACTIVATED', NOW(), NULL, 1),
('생각보다 간단하네요', 'ACTIVATED', NOW(), NULL, 1),
('자주 해먹을 것 같아요', 'ACTIVATED', NOW(), NULL, 1),
('레시피 감사합니다', 'ACTIVATED', NOW(), NULL, 1),
('조금 맵게 먹어도 좋네요', 'ACTIVATED', NOW(), NULL, 1),
('아이들도 잘 먹어요', 'ACTIVATED', NOW(), NULL, 1),
('고춧가루 조절이 핵심', 'ACTIVATED', NOW(), NULL, 1),
('다음엔 돼지고기로 해볼게요', 'ACTIVATED', NOW(), NULL, 1),
('최고의 집밥 메뉴입니다', 'ACTIVATED', NOW(), NULL, 1),

-- =========================
-- post 2 (된장찌개)
-- =========================
('된장 향이 너무 좋아요', 'ACTIVATED', NOW(), NULL, 2),
('집된장으로 해야 맛있네요', 'ACTIVATED', NOW(), NULL, 2),
('애호박 넣으면 최고', 'ACTIVATED', NOW(), NULL, 2),
('구수해서 계속 먹게 돼요', 'ACTIVATED', NOW(), NULL, 2),
('두부는 필수네요', 'ACTIVATED', NOW(), NULL, 2),
('레시피 간단해서 좋아요', 'ACTIVATED', NOW(), NULL, 2),
('밥이랑 잘 어울려요', 'ACTIVATED', NOW(), NULL, 2),
('멸치육수 쓰셨나요?', 'ACTIVATED', NOW(), NULL, 2),
('자취생 필수 레시피', 'ACTIVATED', NOW(), NULL, 2),
('건강한 느낌이에요', 'ACTIVATED', NOW(), NULL, 2),
('간 맞추는 게 중요하네요', 'ACTIVATED', NOW(), NULL, 2),
('다시마 넣어도 좋을 듯', 'ACTIVATED', NOW(), NULL, 2),
('국물이 진짜 좋아요', 'ACTIVATED', NOW(), NULL, 2),
('매일 먹어도 질리지 않음', 'ACTIVATED', NOW(), NULL, 2),
('완벽한 한식입니다', 'ACTIVATED', NOW(), NULL, 2),

-- =========================
-- post 3 (짜장면)
-- =========================
('집에서 짜장면 가능하네요', 'ACTIVATED', NOW(), NULL, 3),
('생각보다 쉬워요', 'ACTIVATED', NOW(), NULL, 3),
('춘장 볶는 게 핵심', 'ACTIVATED', NOW(), NULL, 3),
('고기 많이 넣으면 좋아요', 'ACTIVATED', NOW(), NULL, 3),
('아이들이 좋아해요', 'ACTIVATED', NOW(), NULL, 3),
('중식 느낌 제대로네요', 'ACTIVATED', NOW(), NULL, 3),
('양파가 중요하네요', 'ACTIVATED', NOW(), NULL, 3),
('배달 안 시켜도 될 듯', 'ACTIVATED', NOW(), NULL, 3),
('소스가 진짜 맛있어요', 'ACTIVATED', NOW(), NULL, 3),
('면 삶기 중요합니다', 'ACTIVATED', NOW(), NULL, 3),
('생각보다 간단함', 'ACTIVATED', NOW(), NULL, 3),
('집에서 이게 되네요', 'ACTIVATED', NOW(), NULL, 3),
('진짜 중국집 느낌', 'ACTIVATED', NOW(), NULL, 3),
('다음엔 짬뽕 도전', 'ACTIVATED', NOW(), NULL, 3),
('완전 성공 레시피', 'ACTIVATED', NOW(), NULL, 3),

-- =========================
-- post 4 (볶음밥)
-- =========================
('볶음밥 진짜 쉽네요', 'ACTIVATED', NOW(), NULL, 4),
('계란이 핵심', 'ACTIVATED', NOW(), NULL, 4),
('간단하지만 맛있어요', 'ACTIVATED', NOW(), NULL, 4),
('남은 밥 활용 최고', 'ACTIVATED', NOW(), NULL, 4),
('불맛 내는 게 포인트', 'ACTIVATED', NOW(), NULL, 4),
('야채 넣으면 더 좋아요', 'ACTIVATED', NOW(), NULL, 4),
('간장 살짝 넣으면 굿', 'ACTIVATED', NOW(), NULL, 4),
('자취 필수 메뉴', 'ACTIVATED', NOW(), NULL, 4),
('진짜 맛있게 됐어요', 'ACTIVATED', NOW(), NULL, 4),
('간단해서 좋아요', 'ACTIVATED', NOW(), NULL, 4),
('계란 두 개 추천', 'ACTIVATED', NOW(), NULL, 4),
('후추 꼭 넣으세요', 'ACTIVATED', NOW(), NULL, 4),
('중식 느낌 제대로', 'ACTIVATED', NOW(), NULL, 4),
('집에서도 가능하네요', 'ACTIVATED', NOW(), NULL, 4),
('최고의 한 끼', 'ACTIVATED', NOW(), NULL, 4);