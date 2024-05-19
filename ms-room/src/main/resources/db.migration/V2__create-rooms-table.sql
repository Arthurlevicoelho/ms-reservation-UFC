CREATE TABLE IF NOT EXISTS room.rooms (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    number INTEGER NOT NULL,
    capacity INTEGER NOT NULL,
    status_room room.status_room_enum
)