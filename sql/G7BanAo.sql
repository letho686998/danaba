/****** Object:  Table [dbo].[ChatLieu]    Script Date: 12/3/2023 10:46:43 PM ******/
CREATE DATABASE G7BanAo

SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[ChatLieu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[ChieuDaiTay]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[ChieuDaiTay](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[ChucVu]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[ChucVu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](30) NULL,
	[moTa] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[CoAo]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[CoAo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[DanhMuc]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[DanhMuc](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[HoaDon]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[HoaDon](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[maHoaDon] [varchar](20) NULL,
	[idNhanVien] [int] NULL,
	[idKhachHang] [int] NULL,
	[hinhThucThanhToan] [nvarchar](20) NULL,
	[tongTien] [decimal](20, 2) NULL,
	[tongTienGiam] [decimal](20, 2) NULL,
	[ngayTao] [datetime2](7) NULL,
	[trangThai] [bit] NULL,
	[idVoucher] [int] NULL,
	[ghiChu] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[HoaDonChiTiet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idSanPhamChiTiet] [int] NULL,
	[idHoaDon] [int] NULL,
	[soLuong] [int] NULL,
	[giaTien] [decimal](20, 2) NULL,
	[thanhTien] [decimal](20, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[KhachHang]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[KhachHang](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[sdt] [varchar](20) NULL,
	[email] [varchar](20) NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
	[diaChi] [nvarchar](255) NULL,
	[trangThai] [int] NULL,
	[ngayTao] [datetime] NULL,
	[ngaySua] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[KichCo]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[KichCo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[MauSac]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[MauSac](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[NhanVien]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[NhanVien](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[idChucVu] [int] NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[email] [varchar](50) NULL,
	[sdt] [varchar](20) NULL,
	[gioiTinh] [bit] NULL,
	[ngaySinh] [date] NULL,
	[matKhau] [varchar](256) NULL,
	[diaChi] [nvarchar](255) NULL,
	[trangThai] [int] NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[SanPham]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[SanPham](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[isDelete] [bit] NULL,
	[idDanhMuc] [int] NULL,
	[idThuongHieu] [int] NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[SanPhamChiTiet]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[SanPhamChiTiet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[idChatLieu] [int] NULL,
	[idChieuDaiTay] [int] NULL,
	[idKichCo] [int] NULL,
	[idMauSac] [int] NULL,
	[idSanPham] [int] NULL,
	[idCoAo] [int] NULL,
	[idXuatXu] [int] NULL,
	[soLuong] [int] NULL,
	[giaBan] [decimal](20, 2) NULL,
	[moTa] [nvarchar](255) NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
	[trangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[ThuongHieu]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[ThuongHieu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](7) NULL,
	[ten] [nvarchar](50) NULL,
	[ngayTao] [datetime2](7) NULL,
	[ngaySua] [datetime2](7) NULL,
	[isDelete] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[Voucher]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[Voucher](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ma] [varchar](20) NULL,
	[ten] [varchar](20) NULL,
	[loaiGiamGia] [bit] NULL,
	[ngayBatDau] [datetime2](7) NULL,
	[ngayKetThuc] [datetime2](7) NULL,
	[giaTriGiam] [int] NULL,
	[GiaTriDhToiThieu] [decimal](20, 2) NULL,
	[trangThai] [int] NULL,
	[soLuong] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[XuatXu]    Script Date: 12/3/2023 10:46:43 PM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[XuatXu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ten] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT ((0)) FOR [trangThai]

ALTER TABLE [dbo].[KhachHang] ADD  DEFAULT ((0)) FOR [trangThai]

ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [trangThai]

ALTER TABLE [dbo].[SanPhamChiTiet] ADD  DEFAULT ((0)) FOR [trangThai]

ALTER TABLE [dbo].[Voucher] ADD  DEFAULT ((0)) FOR [trangThai]

ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon.IdkhachHang] FOREIGN KEY([idKhachHang])
REFERENCES [dbo].[KhachHang] ([id])

ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon.IdkhachHang]

ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon.IdnhanVien] FOREIGN KEY([idNhanVien])
REFERENCES [dbo].[NhanVien] ([id])

ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon.IdnhanVien]

ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FK_HoaDon.IdVoucher] FOREIGN KEY([idVoucher])
REFERENCES [dbo].[Voucher] ([id])

ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon.IdVoucher]

ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonChiTiet.idHoaDon] FOREIGN KEY([idHoaDon])
REFERENCES [dbo].[HoaDon] ([id])

ALTER TABLE [dbo].[HoaDonChiTiet] CHECK CONSTRAINT [FK_HoaDonChiTiet.idHoaDon]

ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_HoaDonChiTiet.idSanPhamChiTiet] FOREIGN KEY([idSanPhamChiTiet])
REFERENCES [dbo].[SanPhamChiTiet] ([id])

ALTER TABLE [dbo].[HoaDonChiTiet] CHECK CONSTRAINT [FK_HoaDonChiTiet.idSanPhamChiTiet]

ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien.IdChucVu] FOREIGN KEY([idChucVu])
REFERENCES [dbo].[ChucVu] ([id])

ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien.IdChucVu]

ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_SanPham.idDanhMuc] FOREIGN KEY([idDanhMuc])
REFERENCES [dbo].[DanhMuc] ([id])

ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_SanPham.idDanhMuc]

ALTER TABLE [dbo].[SanPham]  WITH CHECK ADD  CONSTRAINT [FK_SanPham.idThuonghieu] FOREIGN KEY([idThuongHieu])
REFERENCES [dbo].[ThuongHieu] ([id])

ALTER TABLE [dbo].[SanPham] CHECK CONSTRAINT [FK_SanPham.idThuonghieu]

ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet.idChatLieu] FOREIGN KEY([idChatLieu])
REFERENCES [dbo].[ChatLieu] ([id])

ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet.idChatLieu]

ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet.idChieuDaiTay] FOREIGN KEY([idChieuDaiTay])
REFERENCES [dbo].[ChieuDaiTay] ([id])

ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet.idChieuDaiTay]

ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet.idCoAo] FOREIGN KEY([idCoAo])
REFERENCES [dbo].[CoAo] ([id])

ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet.idCoAo]

ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet.idKichCo] FOREIGN KEY([idKichCo])
REFERENCES [dbo].[KichCo] ([id])

ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet.idKichCo]

ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet.idMauSac] FOREIGN KEY([idMauSac])
REFERENCES [dbo].[MauSac] ([id])

ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet.idMauSac]

ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet.idSanPham] FOREIGN KEY([idSanPham])
REFERENCES [dbo].[SanPham] ([id])

ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet.idSanPham]

ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD  CONSTRAINT [FK_SanPhamChiTiet.idXuatXu] FOREIGN KEY([idXuatXu])
REFERENCES [dbo].[XuatXu] ([id])

ALTER TABLE [dbo].[SanPhamChiTiet] CHECK CONSTRAINT [FK_SanPhamChiTiet.idXuatXu]

