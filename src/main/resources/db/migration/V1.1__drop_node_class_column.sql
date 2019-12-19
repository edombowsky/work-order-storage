-------------------------------------------------------------------------------
--Drop column node_class from work_orders table
-------------------------------------------------------------------------------
ALTER TABLE datafabric_workorder.work_orders
DROP COLUMN node_class;
