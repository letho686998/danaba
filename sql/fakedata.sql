-- Chức vụ
INSERT INTO ChucVu (ma, ten, moTa)
VALUES
('CV00001', N'Quản lý', N'Chức vụ quản lý'),
('CV00002', N'Nhân viên', N'Chức vụ nhân viên');

-- Nhân viên
INSERT INTO NhanVien (idChucVu, ma, ten, email, sdt, gioiTinh, ngaySinh, matKhau, diaChi, trangThai, ngayTao, ngaySua)
VALUES
(1, 'NV00001', N'Lê Văn Dũng', 'dunglv@gmail.com', '0123456789', 1, '1990-01-01', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'123 Đường ABC, Hà Nội', 0, GETDATE(), GETDATE()),
(1, 'NV00002', N'Lê Thị Thơ', 'tholt@gmail.com', '0123456788', 0, '1995-05-05', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'456 Đường XYZ, Hà Nội', 0, GETDATE(), GETDATE()),
(1, 'NV00003', N'Nguyễn Bá Cường', 'cuongnb@gmail.com', '0983861011', 0, '1995-05-05', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'789 Đường ZXC, Hà Nội', 0, GETDATE(), GETDATE());

-- Chất liệu
INSERT INTO ChatLieu (ma, ten, ngayTao, ngaySua, isDelete)
VALUES
('TT00000', N'Tất cả', GETDATE(), GETDATE(), 0),
('TT00001', N'Cotton', GETDATE(), GETDATE(), 0),
('TT00002', N'Polyester', GETDATE(), GETDATE(), 0),
('TT00003', N'Rayon', GETDATE(), GETDATE(), 0),
('TT00004', N'Linen', GETDATE(), GETDATE(), 0),
('TT00005', N'Silk', GETDATE(), GETDATE(), 0);

-- Chiều dài tay
INSERT INTO ChieuDaiTay (ma, ten, ngayTao, ngaySua, isDelete)
VALUES
('TT00000', N'Tất cả', GETDATE(), GETDATE(), 0),
('TT00005', N'Ngắn tay', GETDATE(), GETDATE(), 0),
('TT00006', N'Dài tay', GETDATE(), GETDATE(), 0),
('TT00007', N'3/4 tay', GETDATE(), GETDATE(), 0),
('TT00008', N'Tay lỡ', GETDATE(), GETDATE(), 0),
('TT00009', N'Tay phồng', GETDATE(), GETDATE(), 0);

-- Cổ áo
INSERT INTO CoAo (ma, ten, ngayTao, ngaySua, isDelete)
VALUES
('TT00000', N'Tất cả', GETDATE(), GETDATE(), 0),
('TT00010', N'Không có cổ', GETDATE(), GETDATE(), 0),
('TT00011', N'Cổ tròn', GETDATE(), GETDATE(), 0),
('TT00012', N'Cổ V', GETDATE(), GETDATE(), 0),
('TT00013', N'Cổ bẻ', GETDATE(), GETDATE(), 0),
('TT00014', N'Cổ cấp', GETDATE(), GETDATE(), 0);

-- Danh mục
INSERT INTO DanhMuc (ma, ten, ngayTao, ngaySua, isDelete)
VALUES
('TT00000', N'Tất cả', GETDATE(), GETDATE(), 0),
('TT00015', N'Công sở', GETDATE(), GETDATE(), 0),
('TT00016', N'Đường phố', GETDATE(), GETDATE(), 0),
('TT00017', N'Sportswear', GETDATE(), GETDATE(), 0),
('TT00018', N'Bohemian', GETDATE(), GETDATE(), 0),
('TT00019', N'Formal', GETDATE(), GETDATE(), 0);

-- Kích cỡ
INSERT INTO KichCo (ma, ten, ngayTao, ngaySua, isDelete)
VALUES
('TT00000', N'Tất cả', GETDATE(), GETDATE(), 0),
('TT00020', N'XS', GETDATE(), GETDATE(), 0),
('TT00021', N'S', GETDATE(), GETDATE(), 0),
('TT00022', N'M', GETDATE(), GETDATE(), 0),
('TT00023', N'L', GETDATE(), GETDATE(), 0),
('TT00024', N'XL', GETDATE(), GETDATE(), 0);

-- Màu sắc
INSERT INTO MauSac (ma, ten, ngayTao, ngaySua, isDelete)
VALUES
('TT00000', N'Tất cả', GETDATE(), GETDATE(), 0),
('TT00025', N'Trắng', GETDATE(), GETDATE(), 0),
('TT00026', N'Đen', GETDATE(), GETDATE(), 0),
('TT00027', N'Đỏ', GETDATE(), GETDATE(), 0),
('TT00028', N'Xanh dương', GETDATE(), GETDATE(), 0),
('TT00029', N'Hồng', GETDATE(), GETDATE(), 0);

-- Thương hiệu
INSERT INTO ThuongHieu (ma, ten, ngayTao, ngaySua, isDelete)
VALUES
('TT00000', N'Tất cả', GETDATE(), GETDATE(), 0),
('TT00030', N'Adidas', GETDATE(), GETDATE(), 0),
('TT00031', N'Nike', GETDATE(), GETDATE(), 0),
('TT00032', N'Puma', GETDATE(), GETDATE(), 0),
('TT00033', N'Converse', GETDATE(), GETDATE(), 0),
('TT00034', N'Vans', GETDATE(), GETDATE(), 0);

-- Xuất xứ
INSERT INTO XuatXu (ten)
VALUES
(N'Tất cả'),
(N'Việt Nam'),
(N'Hàn Quốc'),
(N'Nhật Bản'),
(N'Mỹ'),
(N'Trung Quốc');

-- Sản phẩm
INSERT INTO SanPham (ma, ten, isDelete, idDanhMuc, idThuongHieu, ngayTao, ngaySua)
VALUES
('SP00001', N'Áo thun nam Adidas', 0, 2, 2, GETDATE(), GETDATE()),
('SP00002', N'Áo thun nữ Nike', 0, 3, 3, GETDATE(), GETDATE()),
('SP00003', N'Áo thun nam thời trang Adidas', 0, 2, 2, GETDATE(), GETDATE()),
('SP00004', N'Áo thun nữ thời trang Nike', 0, 2, 3, GETDATE(), GETDATE()),
('SP00005', N'Áo thun nam Puma', 0, 3, 4, GETDATE(), GETDATE()),
('SP00006', N'Áo thun nam Converse', 0, 2, 5, GETDATE(), GETDATE()),
('SP00007', N'Áo thun nam Vans', 0, 3, 6, GETDATE(), GETDATE()),
('SP00008', N'Áo thun chất lượng cao Puma', 0, 2, 4, GETDATE(), GETDATE()),
('SP00009', N'Áo thun Puma', 0, 3, 4, GETDATE(), GETDATE()),
('SP00010', N'Áo thun Adidas', 0, 3, 2, GETDATE(), GETDATE()),
('SP00011', N'Áo thun Nike', 0, 2, 3, GETDATE(), GETDATE()),
('SP00012', N'Áo thun trẻ trung Puma', 0, 4, 4, GETDATE(), GETDATE());

-- Sản phẩm chi tiết
INSERT INTO SanPhamChiTiet (ma, idChatLieu, idChieuDaiTay, idKichCo, idMauSac, idSanPham, idCoAo, idXuatXu, soLuong, giaBan, moTa, ngayTao, ngaySua, trangThai)
VALUES
('SPCT001', 2, 2, 6, 6, 1, 6, 6, 50, 299000, N'Áo thun nam Adidas size M, màu trắng', GETDATE(), GETDATE(), 0),
('SPCT002', 3, 2, 2, 2, 1, 2, 2, 30, 329000, N'Áo thun nam Adidas size L, màu đen', GETDATE(), GETDATE(), 0),
('SPCT003', 4, 3, 3, 3, 1, 3, 3, 40, 299000, N'Áo thun nam Adidas size XL, màu đỏ', GETDATE(), GETDATE(), 0),
('SPCT004', 2, 4, 4, 4, 1, 4, 4, 25, 349000, N'Áo thun nam Adidas size M, màu xanh dương', GETDATE(), GETDATE(), 0),
('SPCT005', 3, 5, 5, 5, 1, 5, 5, 35, 379000, N'Áo thun nam Adidas size L, màu hồng', GETDATE(), GETDATE(), 0);

INSERT INTO SanPhamChiTiet (ma, idChatLieu, idChieuDaiTay, idKichCo, idMauSac, idSanPham, idCoAo, idXuatXu, soLuong, giaBan, moTa, ngayTao, ngaySua, trangThai)
VALUES
('SPCT006', 4, 6, 6, 6, 2, 6, 6, 45, 349000, N'Áo thun nữ Nike size M, màu trắng', GETDATE(), GETDATE(), 0),
('SPCT007', 2, 2, 2, 2, 2, 2, 2, 30, 379000, N'Áo thun nữ Nike size L, màu đen', GETDATE(), GETDATE(), 0),
('SPCT008', 3, 3, 3, 3, 2, 3, 3, 40, 349000, N'Áo thun nữ Nike size XL, màu đỏ', GETDATE(), GETDATE(), 0),
('SPCT009', 5, 4, 4, 4, 2, 4, 4, 25, 389000, N'Áo thun nữ Nike size M, màu xanh dương', GETDATE(), GETDATE(), 0),
('SPCT010', 2, 5, 5, 5, 2, 5, 2, 35, 419000, N'Áo thun nữ Nike size L, màu hồng', GETDATE(), GETDATE(), 0);

INSERT INTO SanPhamChiTiet (ma, idChatLieu, idChieuDaiTay, idKichCo, idMauSac, idSanPham, idCoAo, idXuatXu, soLuong, giaBan, moTa, ngayTao, ngaySua, trangThai)
VALUES
('SPCT011', 4, 6, 6, 6, 3, 6, 6, 40, 399000, N'Áo polo nam Adidas size M, màu trắng', GETDATE(), GETDATE(), 0),
('SPCT012', 2, 2, 2, 2, 3, 2, 2, 25, 429000, N'Áo polo nam Adidas size L, màu đen', GETDATE(), GETDATE(), 0),
('SPCT013', 3, 3, 3, 3, 3, 3, 3, 35, 399000, N'Áo polo nam Adidas size XL, màu đỏ', GETDATE(), GETDATE(), 0),
('SPCT014', 5, 4, 4, 4, 3, 4, 4, 30, 449000, N'Áo polo nam Adidas size M, màu xanh dương', GETDATE(), GETDATE(), 0),
('SPCT015', 2, 5, 5, 5, 3, 5, 2, 20, 479000, N'Áo polo nam Adidas size L, màu hồng', GETDATE(), GETDATE(), 0);

INSERT INTO SanPhamChiTiet (ma, idChatLieu, idChieuDaiTay, idKichCo, idMauSac, idSanPham, idCoAo, idXuatXu, soLuong, giaBan, moTa, ngayTao, ngaySua, trangThai)
VALUES
('SPCT016', 4, 6, 6, 6, 4, 6, 6, 30, 459000, N'Áo polo nữ Nike size M, màu trắng', GETDATE(), GETDATE(), 0),
('SPCT017', 2, 2, 2, 2, 4, 2, 2, 35, 489000, N'Áo polo nữ Nike size L, màu đen', GETDATE(), GETDATE(), 0),
('SPCT018', 3, 3, 3, 3, 4, 3, 3, 40, 459000, N'Áo polo nữ Nike size XL, màu đỏ', GETDATE(), GETDATE(), 0),
('SPCT019', 5, 4, 4, 4, 4, 4, 4, 25, 519000, N'Áo polo nữ Nike size M, màu xanh dương', GETDATE(), GETDATE(), 0),
('SPCT020', 2, 5, 5, 5, 4, 5, 2, 30, 549000, N'Áo polo nữ Nike size L, màu hồng', GETDATE(), GETDATE(), 0);

INSERT INTO SanPhamChiTiet (ma, idChatLieu, idChieuDaiTay, idKichCo, idMauSac, idSanPham, idCoAo, idXuatXu, soLuong, giaBan, moTa, ngayTao, ngaySua, trangThai)
VALUES
('SPCT021', 4, 6, 6, 6, 5, 6, 6, 25, 599000, N'Áo hoodie nam Puma size M, màu trắng', GETDATE(), GETDATE(), 0),
('SPCT022', 3, 2, 2, 2, 5, 2, 2, 20, 629000, N'Áo hoodie nam Puma size L, màu đen', GETDATE(), GETDATE(), 0),
('SPCT023', 5, 3, 3, 3, 5, 3, 3, 30, 599000, N'Áo hoodie nam Puma size XL, màu đỏ', GETDATE(), GETDATE(), 0),
('SPCT024', 2, 4, 4, 4, 5, 4, 4, 35, 649000, N'Áo hoodie nam Puma size M, màu xanh dương', GETDATE(), GETDATE(), 0),
('SPCT025', 3, 5, 5, 5, 5, 5, 2, 40, 679000, N'Áo hoodie nam Puma size L, màu hồng', GETDATE(), GETDATE(), 0);


INSERT INTO KhachHang (ma, ten) VALUES('KH000', N'Khách lẻ');

























