USE restorante;


DELIMITER $$

CREATE TRIGGER `trg_stock_cantity_update` BEFORE UPDATE ON warehouse FOR EACH ROW

BEGIN

    IF ( NEW.pieces <= 0) THEN
        -- bad data
        SET @ID_P = NULL;
        SELECT id_Product INTO @ID_P FROM warehouse WHERE pieces = 0; 
        DELETE FROM product WHERE id = @ID_P;
    END IF;
    
END$$

DELIMITER ;